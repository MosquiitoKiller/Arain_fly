package ru.mosquiito.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class RegistrationDto {

    @Size(max = 128)
    @Email
    private String email;

    @Size(max = 128)
    @NotBlank
    private String password;

    @Size(max = 128)
    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String captcha;
}
