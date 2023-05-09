package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.Hotel;
import ru.mosquiito.domain.Tour;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByTour(Tour tour);
}
