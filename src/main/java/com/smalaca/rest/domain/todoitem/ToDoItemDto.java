package com.smalaca.rest.domain.todoitem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ToDoItemDto {
    private final Long id;
    private final String name;
    private final String notes;
    private final String assignee;
}
