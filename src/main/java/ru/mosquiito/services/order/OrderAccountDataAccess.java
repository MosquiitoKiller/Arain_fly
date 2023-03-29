package ru.mosquiito.services.order;

import ru.mosquiito.domain.Account;

public interface OrderAccountDataAccess {
    Account findByEmail(String email);
}
