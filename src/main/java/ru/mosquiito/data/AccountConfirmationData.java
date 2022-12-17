package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.AccountConfirmation;
import ru.mosquiito.repositories.AccountConfirmationRepository;
import ru.mosquiito.services.accountConfirmation.AccountConfirmationDataAccess;
import ru.mosquiito.services.recover.RecoverConfirmationDataAccess;

public class AccountConfirmationData implements AccountConfirmationDataAccess, RecoverConfirmationDataAccess {

    @Inject
    private AccountConfirmationRepository accountConfirmationRepository;

    @Override
    public AccountConfirmation findByCode(String code) {
        return accountConfirmationRepository.findByCode(code).orElse(null);
    }

    @Override
    public AccountConfirmation findById(Integer id) {
        return accountConfirmationRepository.findById(id).orElse(null);
    }
}
