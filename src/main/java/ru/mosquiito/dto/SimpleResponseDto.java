package ru.mosquiito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleResponseDto {
    private boolean status;
    private Integer code;
    private String message;

    public SimpleResponseDto(boolean status) {
        this.status = status;
    }

    public SimpleResponseDto(Integer code, String message) {
        this.status=false;
        this.code = code;
        this.message = message;
    }
}
