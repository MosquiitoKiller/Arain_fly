package ru.mosquiito.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import ru.mosquiito.dto.FormOrderRequest;
import ru.mosquiito.dto.SimpleResponseDto;
import ru.mosquiito.services.order.IOrderService;

import javax.validation.Valid;
import java.security.Principal;

@Controller("/order")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class OrderController {

    @Inject
    private IOrderService orderService;

    @Post("/formOrder")
    public MutableHttpResponse<?> formOrder(@Body @Valid FormOrderRequest formOrderRequest, Principal principal) {
        SimpleResponseDto responseDto = orderService.formOrder(formOrderRequest, principal.getName());
        return responseDto.isStatus() ?
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) ://200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.valueOf(responseDto.getCode()), responseDto.getMessage());
    }
}
