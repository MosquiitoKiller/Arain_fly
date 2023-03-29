package ru.mosquiito.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class FormOrderRequest {

    @NotNull
    @NotEmpty
    private Long tourId;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private Integer count;

}
