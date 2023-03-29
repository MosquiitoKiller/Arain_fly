package ru.mosquiito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mosquiito.domain.City;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private String name;

    private String country;

    public CityDto(City city) {
        this.name = city.getName();
        this.country = city.getCountry().getName();
    }
}
