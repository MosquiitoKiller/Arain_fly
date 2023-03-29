package ru.mosquiito.controllers;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mosquiito.dto.RecoverRequestDto;
import ru.mosquiito.dto.RegistrationDto;
import ru.mosquiito.dto.SimpleResponseDto;
import ru.mosquiito.services.accountConfirmation.IAccountConfirmationService;
import ru.mosquiito.services.auth.IRegistrationService;
import ru.mosquiito.services.recover.IRecoverService;
import ru.mosquiito.utils.CaptchaUtils;

import javax.validation.Valid;
import java.io.IOException;

@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Inject
    private CaptchaUtils captchaUtils;

    @Inject
    private IRegistrationService registrationService;

    @Inject
    private IAccountConfirmationService accountConfirmationService;

    @Inject
    private IRecoverService recoverService;

    @Get(value = "/captcha", produces = MediaType.IMAGE_PNG)
    public MutableHttpResponse<?> captcha() throws IOException {
        return captchaUtils.captcha();
    }

    @Post("/registration")
    public MutableHttpResponse<?> registration(@Valid @Body RegistrationDto registrationDto, @CookieValue("JCaptcha") String session) {
        if (!captchaUtils.silentlyValidateCaptcha(session, registrationDto.getCaptcha())) {
            log.warn("Ошибка валидации капчи для почты {}", registrationDto.getEmail());
            return SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.FORBIDDEN);//403
        }
        SimpleResponseDto responseDto = registrationService.registration(registrationDto);

        return responseDto.isStatus() ?
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) ://200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.valueOf(responseDto.getCode()), responseDto.getMessage());
    }

    @Get("/confirm/{code}")
    public MutableHttpResponse<?> confirm(@PathVariable String code) {
        SimpleResponseDto responseDto = accountConfirmationService.confirm(code);
        return responseDto.isStatus() ?
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) ://200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.valueOf(responseDto.getCode()), responseDto.getMessage());
    }

    @Get("/recover/send/{email}")
    public MutableHttpResponse<?> sendRecover(@PathVariable String email) {
        SimpleResponseDto responseDto = recoverService.send(email);
        return responseDto.isStatus() ?
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) ://200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.valueOf(responseDto.getCode()), responseDto.getMessage());
    }

    @Post("/recover")
    public MutableHttpResponse<?> recover(@Valid @Body RecoverRequestDto recoverRequestDto) {
        SimpleResponseDto responseDto = recoverService.recover(recoverRequestDto);
        return responseDto.isStatus() ?
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) ://200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.valueOf(responseDto.getCode()), responseDto.getMessage());
    }
}