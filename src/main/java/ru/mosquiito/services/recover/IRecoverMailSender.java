package ru.mosquiito.services.recover;

import ru.mosquiito.domain.Account;

public interface IRecoverMailSender {
    boolean send(Account account);
}
