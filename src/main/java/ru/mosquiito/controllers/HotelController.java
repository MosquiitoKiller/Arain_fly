package ru.mosquiito.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import ru.mosquiito.dto.HotelDto;
import ru.mosquiito.services.hotel.IHotelService;

import java.util.List;

@Controller("/hotel")
@Secured(SecurityRule.IS_ANONYMOUS)
public class HotelController {

    @Inject
    private IHotelService hotelService;

    @Get("/allByTour/{tourId}")
    public List<HotelDto> getAllByFilter(@PathVariable Long tourId) {
        return hotelService.getAllByTour(tourId);
    }
}
