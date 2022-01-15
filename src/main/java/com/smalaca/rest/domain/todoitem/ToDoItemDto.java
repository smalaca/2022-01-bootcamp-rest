package com.smalaca.rest.domain.todoitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Serialization: Object to JSON
 * Deserialization: JSON to Object
 */
@Getter                 // to serialize; to deserialize
@NoArgsConstructor      // to deserialize
@AllArgsConstructor     // to create in the code
public class ToDoItemDto {
    private Long id;
    private String name;
    private String notes;
    private String assignee;
}
