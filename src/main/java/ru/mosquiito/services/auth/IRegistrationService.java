package ru.mosquiito.services.auth;

import ru.mosquiito.dto.RegistrationDto;
import ru.mosquiito.dto.SimpleResponseDto;

public interface IRegistrationService {

    SimpleResponseDto registration(RegistrationDto registrationDto);
}
