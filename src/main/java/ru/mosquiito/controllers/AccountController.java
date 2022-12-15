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
import ru.mosquiito.dto.RegistrationDto;
import ru.mosquiito.dto.SimpleResponseDto;
import ru.mosquiito.services.auth.IRegistrationService;
import ru.mosquiito.utils.CaptchaUtils;

import javax.validation.Valid;
import java.io.IOException;

@Controller("/auth")
@Secured((SecurityRule.IS_ANONYMOUS))
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Inject
    private CaptchaUtils captchaUtils;

    @Inject
    private IRegistrationService registrationService;

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
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.OK) : //200
                SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.BAD_REQUEST).body(responseDto.getMessage());//400
    }
}