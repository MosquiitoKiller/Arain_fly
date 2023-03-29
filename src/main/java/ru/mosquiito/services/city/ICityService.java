package ru.mosquiito.services.city;

import ru.mosquiito.dto.CityDto;

import java.util.List;

public interface ICityService {
    List<CityDto> getAll();
    CityDto getById(Long id);
}
