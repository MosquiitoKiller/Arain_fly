package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.City;
import ru.mosquiito.domain.Country;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCountry(Country country);
}
