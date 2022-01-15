package com.smalaca.rest.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ToDoItemNotFoundException extends RuntimeException {
    private final Long id;

    public ToDoItemNotFoundException(Long id) {
        this.id = id;
    }
}
