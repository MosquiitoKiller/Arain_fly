package ru.mosquiito.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import ru.mosquiito.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
