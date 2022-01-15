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
    private String headers;

    public ToDoItem(String name, String notes, String assignee, String headers) {
        this.name = name;
        this.notes = notes;
        this.assignee = assignee;
        this.headers = headers;
    }

    public ToDoItemDto asDto() {
        return new ToDoItemDto(id, name, notes, assignee, headers);
    }

    public Long getId() {
        return id;
    }

    public void update(ToDoItemDto dto) {
        notes = dto.getNotes();
        assignee = dto.getAssignee();
    }
}
