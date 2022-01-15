package com.smalaca.rest.domain.todoitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
    List<ToDoItem> findAllByNameOrAssignee(String name, String assignee);

    List<ToDoItem> findAllByIdIn(List<Long> ids);
}
