package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
