package ru.mosquiito.services.recover;

import ru.mosquiito.domain.Account;

public interface RecoverAccountDataAccess {
    Account findByEmail(String email);
    Account update(Account account);
    Account findById(Integer id);
}
