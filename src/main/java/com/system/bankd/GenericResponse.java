package com.system.bankd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse<T> {
    private String message;
    private T result;

    public GenericResponse(String message, T result) {
        this.message = message;
        this.result = result;
    }
}
