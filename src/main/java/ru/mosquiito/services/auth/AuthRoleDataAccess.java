package ru.mosquiito.services.auth;

import ru.mosquiito.domain.Role;

public interface AuthRoleDataAccess {
    Role findById(Integer id);
}
