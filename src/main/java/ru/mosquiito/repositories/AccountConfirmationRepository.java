package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.AccountConfirmation;

import java.util.Optional;

@Repository
public interface AccountConfirmationRepository extends JpaRepository<AccountConfirmation, Long> {
    Optional<AccountConfirmation> findByCode(String code);
}
