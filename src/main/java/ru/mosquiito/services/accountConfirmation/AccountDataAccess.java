package ru.mosquiito.services.accountConfirmation;

import ru.mosquiito.domain.Account;

public interface AccountDataAccess {
    Account findById(Integer id);
    Account update(Account account);
}
