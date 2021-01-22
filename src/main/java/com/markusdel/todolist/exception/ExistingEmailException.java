package com.markusdel.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingEmailException extends Exception{

    public ExistingEmailException(String email) {
        super(String.format("Email: %s is already in use", email));
    }
}
