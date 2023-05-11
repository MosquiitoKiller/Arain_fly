package ru.mosquiito.services.admin;

import ru.mosquiito.dto.AccountDto;

import java.util.List;

public interface IAdminService {
    List<AccountDto> getAllUsers();

    boolean blockUser(Long id);
}
