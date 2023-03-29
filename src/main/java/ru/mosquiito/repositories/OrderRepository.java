package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.mosquiito.domain.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
}
