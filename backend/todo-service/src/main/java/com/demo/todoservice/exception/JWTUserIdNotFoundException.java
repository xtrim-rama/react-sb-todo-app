package com.demo.todoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class JWTUserIdNotFoundException extends RuntimeException {

    public JWTUserIdNotFoundException() {
        super("UserId not found in the received Authorization Header");
    }
}
