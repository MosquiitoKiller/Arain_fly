package ru.mosquiito.utils.mail;

import freemarker.template.Configuration;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.InternetAddress;
import java.util.Collection;

public abstract class AbstractMailSender {

    private final Logger log = LoggerFactory.getLogger(AbstractMailSender.class);

    @Inject
    protected Configuration configuration;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.from}")
    private String from;

    protected HtmlEmail init(){
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setAuthentication(username, password);
        email.setCharset("utf-8");
        try {
            email.setFrom(from);
        } catch (EmailException e) {
            log.warn("Incorrect mail settings");
            throw new RuntimeException(e);
        }
        return email;
    }

    protected abstract Collection<InternetAddress> getTo(Object o);

    protected abstract String getSubject(Object o);

    protected abstract String getMessageBody(Object o);

}
