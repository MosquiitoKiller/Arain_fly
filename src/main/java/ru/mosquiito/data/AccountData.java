package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Account;
import ru.mosquiito.repositories.AccountRepository;
import ru.mosquiito.services.accountConfirmation.AccountDataAccess;
import ru.mosquiito.services.admin.AdminAccountDataAccess;
import ru.mosquiito.services.auth.AuthAccountDataAccess;
import ru.mosquiito.services.order.OrderAccountDataAccess;
import ru.mosquiito.services.recover.RecoverAccountDataAccess;

import java.util.List;

public class AccountData implements AuthAccountDataAccess, AccountDataAccess, RecoverAccountDataAccess, OrderAccountDataAccess, AdminAccountDataAccess {

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

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account update(Account account) {
        return accountRepository.update(account);
    }
}
