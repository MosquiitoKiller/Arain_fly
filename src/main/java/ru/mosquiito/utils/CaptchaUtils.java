package ru.mosquiito.utils;

import com.octo.captcha.service.CaptchaService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.netty.cookies.NettyCookie;
import io.micronaut.http.simple.SimpleHttpResponseFactory;
import jakarta.inject.Inject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class CaptchaUtils {

    @Inject
    private CaptchaService captchaService;

    @Value("${captcha.ignore}")
    private boolean ignoreCaptcha;

    public MutableHttpResponse<?> captcha() throws IOException {
        String captchaId = generateCode();
        BufferedImage challenge = (BufferedImage) captchaService.getChallengeForID(captchaId);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ImageIO.write(challenge, "PNG", byteOutputStream);

        MutableHttpResponse<?> mutableHttpResponse = SimpleHttpResponseFactory.INSTANCE.ok(byteOutputStream.toByteArray());
        Set<Cookie> cookies = new HashSet<>();
        NettyCookie nettyCookie = new NettyCookie("JCaptcha", captchaId);
        nettyCookie.httpOnly(true);
        cookies.add(nettyCookie);
        mutableHttpResponse.cookies(cookies);
        return mutableHttpResponse;
    }

    private String generateCode() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.ints(48, 122)
                .filter(i -> (i > 48 && i < 57) || (i > 65 && i < 90) || (i > 97 && i < 122))//0-9 || A-Z || a-z
                .mapToObj(i -> (char) i)
                .limit(64).toString();
    }

    public boolean silentlyValidateCaptcha(String session, String code) {
        try {
            return ignoreCaptcha || captchaService.validateResponseForID(session, code);
        } catch (Exception e) {
            return false;
        }
    }
}
