package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
