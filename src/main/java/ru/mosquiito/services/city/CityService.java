package ru.mosquiito.services.city;

import jakarta.inject.Inject;
import ru.mosquiito.domain.City;
import ru.mosquiito.domain.Country;
import ru.mosquiito.dto.CityDto;

import java.util.List;

public class CityService implements ICityService {

    @Inject
    private CityDataAccess cityDataAccess;

    @Inject
    private CityCountryDataAccess countryDataAccess;

    @Override
    public List<CityDto> getAll() {
        return cityDataAccess.getAll().stream().map(CityDto::new).toList();
    }

    @Override
    public CityDto getById(Long id) {
        City city = cityDataAccess.getById(id);
        return (city == null) ? null : new CityDto(city);
    }

    @Override
    public List<CityDto> getAllByCountry(Long countryId) {
        Country country = countryDataAccess.findById(countryId);
        return country == null ? null : cityDataAccess.getAllByCountry(country);
    }
}
