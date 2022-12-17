package ru.mosquiito.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class RecoverRequestDto {

    @NotBlank
    private String code;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;
}
