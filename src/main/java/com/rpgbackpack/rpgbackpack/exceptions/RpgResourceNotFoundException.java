package com.rpgbackpack.rpgbackpack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RpgResourceNotFoundException extends RuntimeException {

    public RpgResourceNotFoundException(String message) {
        super(message);
    }
}
