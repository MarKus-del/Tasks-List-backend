package com.markusdel.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends Exception{


    public EmailNotFoundException(String email) {
        super("Not found email  " + email);
    }
}
