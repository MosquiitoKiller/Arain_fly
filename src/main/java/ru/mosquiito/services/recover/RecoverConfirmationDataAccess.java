package ru.mosquiito.services.recover;

import ru.mosquiito.domain.AccountConfirmation;

public interface RecoverConfirmationDataAccess {
    AccountConfirmation findByCode(String code);
}
