package ru.mosquiito.config;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.AbstractBackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import freemarker.template.Configuration;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Factory
public class BeanConfig {

    @Value("${paths.ftl.email}")
    private String emailPath;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    Configuration configureEmail() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File(emailPath));
        return configuration;
    }

    @Bean
    CaptchaService captchaService() {
        WordGenerator wordGen = new RandomWordGenerator("0123456789");

        FontGenerator fontGenRandom = new RandomFontGenerator(12, 14,
                new Font[]{
                        new Font("Arial", Font.PLAIN, 10),
                        new Font("Tahoma", Font.PLAIN, 10),
                        new Font("Verdana", Font.PLAIN, 10),
                        new Font("Comic sans MS", Font.PLAIN, 10),
                        new Font("Lucida console", Font.PLAIN, 10),
                });
        Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        ColorGenerator whiteGen = new SingleColorGenerator(white);
        ColorGenerator blackGen = new SingleColorGenerator(black);
        AbstractBackgroundGenerator abstractBackgroundGenerator = new FunkyBackgroundGenerator(120, 30, blackGen, whiteGen, whiteGen, blackGen, 0.1F);

        TextPaster simpleColoredPaster = new RandomTextPaster(4, 6, new SingleColorGenerator(white), true);

        ComposedWordToImage wordToImage = new ComposedWordToImage(
                fontGenRandom,
                abstractBackgroundGenerator,
                simpleColoredPaster);

        CaptchaFactory captchaFactory = new GimpyFactory(wordGen, wordToImage);
        CaptchaEngine imageEngine = new GenericCaptchaEngine(new CaptchaFactory[]{captchaFactory});

        return new GenericManageableCaptchaService(imageEngine, 300, 10000);
    }
}
