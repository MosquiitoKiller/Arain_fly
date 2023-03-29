package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.City;
import ru.mosquiito.repositories.CityRepository;
import ru.mosquiito.services.city.CityDataAccess;

import java.util.List;

public class CityData implements CityDataAccess {

    @Inject
    private CityRepository cityRepository;


    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }
}
