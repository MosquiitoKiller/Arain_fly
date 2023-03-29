package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Order;
import ru.mosquiito.repositories.OrderRepository;
import ru.mosquiito.services.order.OrderDataAccess;

public class OrderData implements OrderDataAccess {

    @Inject
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
