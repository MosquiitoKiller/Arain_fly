package ru.mosquiito.services.order;

import jakarta.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.Order;
import ru.mosquiito.domain.Tour;
import ru.mosquiito.domain.enums.AccountStatus;
import ru.mosquiito.dto.FormOrderRequest;
import ru.mosquiito.dto.SimpleResponseDto;

import java.util.Date;

@Transactional
public class OrderService implements IOrderService {

    @Inject
    private OrderAccountDataAccess accountDataAccess;

    @Inject
    private OrderTourDataAccess tourDataAccess;

    @Inject
    private OrderDataAccess orderDataAccess;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public SimpleResponseDto formOrder(FormOrderRequest formOrderRequest, String username) {
        Account account = accountDataAccess.findByEmail(username);
        if (account == null || account.getAccountStatus() != AccountStatus.ACTIVE)
            return new SimpleResponseDto(403, "Аккаунт не найден или заблокирован");
        Tour tour = tourDataAccess.findById(formOrderRequest.getTourId());
        if (tour == null || !tour.getActive() || formOrderRequest.getCount() > tour.getFreePlaces()) {
            return new SimpleResponseDto(406, "Оформление тура не возможно");
        }

        Order order = new Order(account, tour, formOrderRequest.getCount(),
                tour.getPrice() * formOrderRequest.getCount(), new Date(), false, null);
        tour.setFreePlaces(tour.getFreePlaces() - order.getCount());

        orderDataAccess.save(order);
        tourDataAccess.update(tour);

        return new SimpleResponseDto(true);
    }
}
