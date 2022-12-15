package ru.mosquiito.domain.enums;

import lombok.Getter;

public enum AccountStatus {
    NOT_CONFIRMED(0),
    ACTIVE(1),
    INACTIVE(2);

    @Getter
    private final int status;

    AccountStatus(int status) {
        this.status = status;
    }
}
