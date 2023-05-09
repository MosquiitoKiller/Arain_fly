package ru.mosquiito.services.country;

import ru.mosquiito.dto.CountryDto;

import java.util.List;

public interface ICountryService {
    List<CountryDto> getAll();
    CountryDto getById(Long id);
}
