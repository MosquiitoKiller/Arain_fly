package ru.mosquiito.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import ru.mosquiito.dto.CityDto;
import ru.mosquiito.services.city.ICityService;

import java.util.List;

@Controller("/city")
@Secured(SecurityRule.IS_ANONYMOUS)
public class CityController {

    @Inject
    private ICityService cityService;

    @Get("/all")
    public List<CityDto> getAll() {
        return cityService.getAll();
    }

    @Get("/{id}")
    public CityDto getById(@PathVariable Long id) {
        return cityService.getById(id);
    }

    @Get("/byCountry/{id}")
    public List<CityDto> getAllByCountry(@PathVariable Long id) {
        return cityService.getAllByCountry(id);
    }
}
