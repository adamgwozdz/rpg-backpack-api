package com.rpgbackpack.rpgbackpack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RpgBadRequestException extends RuntimeException{

    public RpgBadRequestException(String message) {
        super(message);
    }
}
