package com.rpgbackpack.rpgbackpack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RpgAuthException extends RuntimeException {

    public RpgAuthException(String message) {
        super(message);
    }
}
