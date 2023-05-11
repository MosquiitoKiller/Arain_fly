package ru.mosquiito.services.admin;

import ru.mosquiito.domain.Account;

import java.util.List;

public interface AdminAccountDataAccess {
    List<Account> getAll();
    Account findById(Long id);
    Account update(Account account);
}
