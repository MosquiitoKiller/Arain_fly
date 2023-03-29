package ru.mosquiito.services.accountConfirmation;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;
import ru.mosquiito.domain.enums.AccountStatus;
import ru.mosquiito.dto.SimpleResponseDto;
import ru.mosquiito.services.auth.ICreateConfirmationService;
import ru.mosquiito.services.recover.ICreateRecoverService;

import java.security.SecureRandom;

@Singleton
public class AccountConfirmationService implements ICreateConfirmationService, IAccountConfirmationService, ICreateRecoverService {

    private static final Logger log = LoggerFactory.getLogger(AccountConfirmationService.class);

    private final int CODE_LENGTH = 64;

    @Inject
    private AccountConfirmationDataAccess accountConfirmationDataAccess;

    @Inject
    private AccountDataAccess accountDataAccess;

    @Override
    public AccountConfirmation create(Account account) {
        return new AccountConfirmation(generateCode(), account);
    }

    @Override
    public SimpleResponseDto confirm(String code) {
        AccountConfirmation accountConfirmation = accountConfirmationDataAccess.findByCode(code);
        if (accountConfirmation == null) {
            log.warn("Ключ активации {} отсутсвует", code);
            return new SimpleResponseDto(404, "Некорректный код подтверждения");
        }
        Account account = accountDataAccess.findById(accountConfirmation.getId());
        if (account == null) {
            log.warn("Не найден аккаунт для кода подтверждения {}", code);
            return new SimpleResponseDto(404, "Не найден аккаунт");
        }
        if (account.getAccountStatus() == AccountStatus.INACTIVE) {
            return new SimpleResponseDto(403, "Аккаунт заблокирован");
        }

        account.setAccountStatus(AccountStatus.ACTIVE);
        accountDataAccess.update(account);
        log.info("Пользователь {} успешно подтвержден", account.getEmail());
        return new SimpleResponseDto(true);
    }

    @Override
    public AccountConfirmation createRecover(Account account) {
        if (account.getAccountConfirmation() == null)
            return new AccountConfirmation(generateCode(), account);

        AccountConfirmation accountConfirmation = accountConfirmationDataAccess.findById(account.getId());
        accountConfirmation.setCode(generateCode());

        return accountConfirmation;
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
