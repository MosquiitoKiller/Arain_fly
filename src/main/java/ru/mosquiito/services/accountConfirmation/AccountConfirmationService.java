package ru.mosquiito.services.accountConfirmation;

import jakarta.inject.Singleton;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;
import ru.mosquiito.services.auth.ICreateConfirmationService;

import java.security.SecureRandom;

@Singleton
public class AccountConfirmationService implements ICreateConfirmationService {

    private final int CODE_LENGTH = 64;

    @Override
    public AccountConfirmation create(Account account) {
        return new AccountConfirmation(generateCode(), account);
    }

    private String generateCode() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> ((char) i))
                .limit(CODE_LENGTH)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
