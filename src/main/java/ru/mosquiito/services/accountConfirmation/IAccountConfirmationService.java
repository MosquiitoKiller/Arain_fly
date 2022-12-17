package ru.mosquiito.services.accountConfirmation;

import ru.mosquiito.dto.SimpleResponseDto;

public interface IAccountConfirmationService {
    SimpleResponseDto confirm(String code);
}
