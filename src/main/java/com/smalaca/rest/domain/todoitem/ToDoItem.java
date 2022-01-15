package com.smalaca.rest.domain.todoitem;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PRIVATE;

@Entity
@NoArgsConstructor(access = PRIVATE)
public class ToDoItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String notes;
    private String assignee;

    public ToDoItem(String name, String notes, String assignee) {
        this.name = name;
        this.notes = notes;
        this.assignee = assignee;
    }

    public ToDoItemDto asDto() {
        return new ToDoItemDto(id, name, notes, assignee);
    }

    public Long getId() {
        return id;
    }
}
