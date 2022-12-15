package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Account;
import ru.mosquiito.repositories.AccountRepository;
import ru.mosquiito.services.auth.AuthAccountDataAccess;

public class AccountData implements AuthAccountDataAccess {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmailIgnoreCase(email).orElse(null);
    }
}
