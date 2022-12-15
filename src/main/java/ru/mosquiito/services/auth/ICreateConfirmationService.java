package ru.mosquiito.services.auth;

import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;

public interface ICreateConfirmationService {
    AccountConfirmation create(Account account);
}
