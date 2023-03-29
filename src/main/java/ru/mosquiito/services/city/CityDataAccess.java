package ru.mosquiito.services.city;

import ru.mosquiito.domain.City;

import java.util.List;

public interface CityDataAccess {
    List<City> getAll();
    City getById(Long id);
}
