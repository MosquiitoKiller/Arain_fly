package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Role;
import ru.mosquiito.repositories.RoleRepository;
import ru.mosquiito.services.auth.AuthRoleDataAccess;

public class RoleData implements AuthRoleDataAccess {

    @Inject
    private RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
