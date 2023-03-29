package ru.mosquiito.services.recover;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;
import ru.mosquiito.dto.RecoverRequestDto;
import ru.mosquiito.dto.SimpleResponseDto;

public class RecoverService implements IRecoverService {

    private final Logger log = LoggerFactory.getLogger(RecoverService.class);

    @Inject
    private RecoverAccountDataAccess recoverAccountDataAccess;

    @Inject
    private RecoverConfirmationDataAccess recoverConfirmationDataAccess;

    @Inject
    private ICreateRecoverService createRecoverService;

    @Inject
    private BCryptPasswordEncoder passwordEncoder;

    @Inject
    private IRecoverMailSender recoverMailSender;

    @Override
    public SimpleResponseDto send(String email) {
        Account account = recoverAccountDataAccess.findByEmail(email);
        if (account == null)
            return new SimpleResponseDto(404, "Не найден аккаунт с почтой " + email);
        AccountConfirmation accountConfirmation = createRecoverService.createRecover(account);
        account.setAccountConfirmation(accountConfirmation);
        account = recoverAccountDataAccess.update(account);

        recoverMailSender.send(account);

        log.info("Запрошен сброс пароля для пользователя {}", account.getEmail());
        return new SimpleResponseDto(true);
    }

    @Override
    public SimpleResponseDto recover(RecoverRequestDto recoverRequestDto) {
        AccountConfirmation accountConfirmation = recoverConfirmationDataAccess.findByCode(recoverRequestDto.getCode());
        if (accountConfirmation == null) {
            log.warn("Ключ активации {} отсутсвует", recoverRequestDto.getCode());
            return new SimpleResponseDto(404, "Неверный код подтверждения");
        }
        if (!recoverRequestDto.getPassword().equals(recoverRequestDto.getPasswordConfirm())) {
            log.warn("Не совпадают пароли для пользователя с кодом подтверждения {}", recoverRequestDto.getCode());
            return new SimpleResponseDto(400, "Не совпадают пароли");
        }
        Account account = recoverAccountDataAccess.findById(accountConfirmation.getId());
        account.setPassword(passwordEncoder.encode(recoverRequestDto.getPassword()));
        recoverAccountDataAccess.update(account);
        log.info("Произведен сброс пароля для аккаунта {}", account.getEmail());
        return new SimpleResponseDto(true);
    }
}
