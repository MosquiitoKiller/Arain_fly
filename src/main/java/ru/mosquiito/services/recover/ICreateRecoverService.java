package ru.mosquiito.services.recover;

import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;

public interface ICreateRecoverService {
    AccountConfirmation createRecover(Account account);
}
