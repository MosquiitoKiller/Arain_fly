package ru.mosquiito.services.recover;

import ru.mosquiito.dto.RecoverRequestDto;
import ru.mosquiito.dto.SimpleResponseDto;

public interface IRecoverService {
    SimpleResponseDto send(String email);
    SimpleResponseDto recover(RecoverRequestDto recoverRequestDto);
}
