package ru.mosquiito.services.admin;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.enums.AccountStatus;
import ru.mosquiito.dto.AccountDto;

import java.util.List;

public class AdminService implements IAdminService{

    @Inject
    private AdminAccountDataAccess accountDataAccess;

    @Override
    public List<AccountDto> getAllUsers() {
        return accountDataAccess.getAll().stream().map(AccountDto::new).toList();
    }

    @Override
    public boolean blockUser(Long id) {
        Account account = accountDataAccess.findById(id);
        if (account == null) return false;
        account.setAccountStatus(AccountStatus.INACTIVE);
        accountDataAccess.update(account);
        return true;
    }
}
