package com.markusdel.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String email) {
        super(String.format("Email: %s  does not match the requested pattern", email));
    }
}
