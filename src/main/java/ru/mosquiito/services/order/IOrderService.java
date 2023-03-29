package ru.mosquiito.services.order;

import ru.mosquiito.dto.FormOrderRequest;
import ru.mosquiito.dto.SimpleResponseDto;

public interface IOrderService {
    SimpleResponseDto formOrder(FormOrderRequest formOrderRequest, String username);
}
