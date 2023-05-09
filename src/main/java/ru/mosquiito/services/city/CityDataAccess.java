package ru.mosquiito.services.city;

import ru.mosquiito.domain.City;
import ru.mosquiito.domain.Country;
import ru.mosquiito.dto.CityDto;

import java.util.List;

public interface CityDataAccess {
    List<City> getAll();

    City getById(Long id);

    List<CityDto> getAllByCountry(Country country);
}
