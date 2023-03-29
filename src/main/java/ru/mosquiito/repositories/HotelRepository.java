package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
