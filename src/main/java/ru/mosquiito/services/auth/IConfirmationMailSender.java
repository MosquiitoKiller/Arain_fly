package ru.mosquiito.services.auth;

import ru.mosquiito.domain.Account;

public interface IConfirmationMailSender {
    boolean send(Account account);
}
