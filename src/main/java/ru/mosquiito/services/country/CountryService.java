package ru.mosquiito.services.country;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Country;
import ru.mosquiito.dto.CountryDto;

import java.util.List;

public class CountryService implements ICountryService {

    @Inject
    private CountryDataAccess countryDataAccess;

    @Override
    public List<CountryDto> getAll() {
        return countryDataAccess.getAll().stream().map(CountryDto::new).toList();
    }

    @Override
    public CountryDto getById(Long id) {
        Country country = countryDataAccess.findById(id);
        return country == null ? null : new CountryDto(country);
    }
}
