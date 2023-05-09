package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Country;
import ru.mosquiito.repositories.CountryRepository;
import ru.mosquiito.services.city.CityCountryDataAccess;
import ru.mosquiito.services.country.CountryDataAccess;

import java.util.List;

public class CountryData implements CountryDataAccess, CityCountryDataAccess {
    @Inject
    private CountryRepository countryRepository;


    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }
}
