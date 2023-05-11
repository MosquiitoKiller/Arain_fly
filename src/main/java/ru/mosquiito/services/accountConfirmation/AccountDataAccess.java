package ru.mosquiito.services.accountConfirmation;

import ru.mosquiito.domain.Account;

public interface AccountDataAccess {
    Account findById(Long id);
    Account update(Account account);
}
