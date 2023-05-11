package ru.mosquiito.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;
import ru.mosquiito.dto.AccountDto;
import ru.mosquiito.services.admin.IAdminService;

import java.util.List;

@Controller("/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {

    @Inject
    private IAdminService adminService;

    @Get("/allUsers")
    public List<AccountDto> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Delete("/user/{id}")
    public boolean blockUser(@PathVariable Long id) {
        return adminService.blockUser(id);
    }
}
