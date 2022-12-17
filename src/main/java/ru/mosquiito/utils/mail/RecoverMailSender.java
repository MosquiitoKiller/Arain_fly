package ru.mosquiito.utils.mail;

import freemarker.template.Template;
import io.micronaut.context.annotation.Value;
import lombok.SneakyThrows;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mosquiito.domain.Account;
import ru.mosquiito.services.recover.IRecoverMailSender;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.StringWriter;
import java.util.*;

public class RecoverMailSender extends AbstractMailSender implements IRecoverMailSender {

    private final String TEMPLATE_NAME = "recover.ftl";

    private final Logger logger = LoggerFactory.getLogger(RecoverMailSender.class);

    @Value("${mail.text.recover.subject}")
    private String subject;

    @Value("${mail.text.recover.href}")
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
            logger.error("Не удалось отправить письмо восстановления для {}", account.getEmail());
            throw new RuntimeException(e);
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
            logger.error("Не корректный почтовый адрес {} . Письмо востановления не отправлено", account.getEmail());
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

        Map<Object, String> data = new HashMap<>();
        data.put("recoverPassUrl", href + account.getAccountConfirmation().getCode());

        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }
}
