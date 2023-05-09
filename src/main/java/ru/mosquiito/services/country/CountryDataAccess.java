package ru.mosquiito.services.country;

import ru.mosquiito.domain.Country;

import java.util.List;

public interface CountryDataAccess {
    List<Country> getAll();
    Country findById(Long id);
}
