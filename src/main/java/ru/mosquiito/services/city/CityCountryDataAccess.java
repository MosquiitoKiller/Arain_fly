package ru.mosquiito.services.city;

import ru.mosquiito.domain.Country;

public interface CityCountryDataAccess {
    Country findById(Long id);
}
