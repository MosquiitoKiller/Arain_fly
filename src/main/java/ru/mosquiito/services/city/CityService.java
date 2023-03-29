package ru.mosquiito.services.city;

import jakarta.inject.Inject;
import ru.mosquiito.domain.City;
import ru.mosquiito.dto.CityDto;

import java.util.List;

public class CityService implements ICityService {

    @Inject
    private CityDataAccess cityDataAccess;

    @Override
    public List<CityDto> getAll() {
        return cityDataAccess.getAll().stream().map(CityDto::new).toList();
    }

    @Override
    public CityDto getById(Long id) {
        City city = cityDataAccess.getById(id);
        return (city == null) ? null : new CityDto(city);
    }
}
