package ru.mosquiito.services.auth;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.mosquiito.domain.Account;
import ru.mosquiito.domain.AccountConfirmation;
import ru.mosquiito.domain.enums.AccountStatus;
import ru.mosquiito.dto.RegistrationDto;
import ru.mosquiito.dto.SimpleResponseDto;

import java.util.Collections;
import java.util.Date;

public class AuthService implements IRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Inject
    private BCryptPasswordEncoder encoder;

    @Inject
    private AuthAccountDataAccess accountDataAccess;

    @Inject
    private AuthRoleDataAccess roleDataAccess;

    @Inject
    private ICreateConfirmationService confirmationService;

    @Inject
    private IConfirmationMailSender mailSender;

    @Override
    public SimpleResponseDto registration(RegistrationDto registrationDto) {
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            log.warn("Не удалось зарегистрировать пользователя {}, пароли не совпадают", registrationDto.getEmail());
            return new SimpleResponseDto(false, "Пароли не совпадают");
        }
        if (accountDataAccess.findByEmail(registrationDto.getEmail()) != null) {
            log.warn("Пользователь с почтой {} уже существует", registrationDto.getEmail());
            return new SimpleResponseDto(false, "Пользователь с почтой " + registrationDto.getEmail() + " уже существует");
        }
        Account account = new Account(registrationDto.getEmail(),
                encoder.encode(registrationDto.getPassword()),
                AccountStatus.NOT_CONFIRMED,
                new Date(),
                Collections.singletonList(roleDataAccess.findById(1)));
        AccountConfirmation accountConfirmation = confirmationService.create(account);
        account.setAccountConfirmation(accountConfirmation);
        accountDataAccess.save(account);
        mailSender.send(account);
        return new SimpleResponseDto(true, "OK");
    }
}
