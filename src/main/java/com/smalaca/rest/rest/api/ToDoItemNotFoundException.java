package com.smalaca.rest.rest.api;

public class ToDoItemNotFoundException extends RuntimeException {
    private final Long id;

    public ToDoItemNotFoundException(Long id) {
        this.id = id;
    }
}
