package ru.mosquiito.utils.mail;

import freemarker.template.Template;
import io.micronaut.context.annotation.Value;
import lombok.SneakyThrows;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mosquiito.domain.Account;
import ru.mosquiito.services.auth.IConfirmationMailSender;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.StringWriter;
import java.util.*;

public class ConfirmationMailSender extends AbstractMailSender implements IConfirmationMailSender {

    private final String TEMPLATE_NAME = "confirmation.ftl";

    private final Logger log = LoggerFactory.getLogger(ConfirmationMailSender.class);

    @Value("${mail.text.confirmation.subject}")
    private String subject;

    @Value("${mail.text.confirmation.href}")
    private String href;

    @Override
    public boolean send(Account account) {
        HtmlEmail htmlEmail = init();
        try {
            htmlEmail.setTo(getTo(account));
            htmlEmail.setSubject(getSubject(account));
            htmlEmail.setHtmlMsg(getMessageBody(account));
            htmlEmail.send();
        } catch (EmailException e) {
            log.error("Не удалось отправить подтверждение для {}", account.getEmail());
            log.error(String.valueOf(e.getStackTrace()));
            return false;
        }
        return true;
    }

    @Override
    protected Collection<InternetAddress> getTo(Object o) {
        Account account = ((Account) o);
        List<InternetAddress> to = new ArrayList<>();
        try {
            to.add(new InternetAddress(account.getEmail()));
        } catch (AddressException e) {
            log.error("Не корректный почтовый адрес {} . Письмо подтверждения не отправлено", account.getEmail());
            throw new RuntimeException(e);
        }
        return to;
    }

    @Override
    protected String getSubject(Object o) {
        return subject;
    }

    @SneakyThrows
    @Override
    protected String getMessageBody(Object o) {
        Account account = ((Account) o);
        Template template = configuration.getTemplate(TEMPLATE_NAME);

        Map<String, Object> data = new HashMap<>();
        data.put("regConfirmationUrl", href + account.getAccountConfirmation().getCode());

        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }
}
