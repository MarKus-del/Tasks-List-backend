package com.markusdel.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{


    public UserNotFoundException(Long id) {
        super("Not found user with id: " + id);
    }
}
