package ru.mosquiito.services.order;

import ru.mosquiito.domain.Order;

public interface OrderDataAccess {
    Order save(Order order);
}
