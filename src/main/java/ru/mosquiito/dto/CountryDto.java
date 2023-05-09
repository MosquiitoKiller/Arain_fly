package ru.mosquiito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mosquiito.domain.Country;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private Long id;

    private String name;

    public CountryDto(final Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
}
