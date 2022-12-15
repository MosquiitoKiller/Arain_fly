package ru.mosquiito.services.auth;

import ru.mosquiito.domain.Account;

public interface AuthAccountDataAccess {
    Account save(Account account);
    Account findByEmail(String email);
}
