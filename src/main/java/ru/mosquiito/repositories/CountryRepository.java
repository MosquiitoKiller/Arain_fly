package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
