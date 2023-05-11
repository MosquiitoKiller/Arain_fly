package ru.mosquiito.services.accountConfirmation;

import ru.mosquiito.domain.AccountConfirmation;

public interface AccountConfirmationDataAccess {
    AccountConfirmation findByCode(String code);
    AccountConfirmation findById(Long id);
}
