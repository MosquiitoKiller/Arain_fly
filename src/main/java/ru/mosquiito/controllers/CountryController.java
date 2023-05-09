package ru.mosquiito.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import ru.mosquiito.dto.CountryDto;
import ru.mosquiito.services.country.ICountryService;

import java.util.List;

@Controller("/country")
@Secured(SecurityRule.IS_ANONYMOUS)
public class CountryController {

    @Inject
    private ICountryService countryService;

    @Get("/all")
    public List<CountryDto> getAll() {
        return countryService.getAll();
    }

    @Get("/{id}")
    public CountryDto getById(@PathVariable Long id) {
        return countryService.getById(id);
    }
}
