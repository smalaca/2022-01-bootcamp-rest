package com.smalaca.rest.rest.api;

import com.smalaca.rest.domain.todoitem.ToDoItem;
import com.smalaca.rest.domain.todoitem.ToDoItemDto;
import com.smalaca.rest.domain.todoitem.ToDoItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/todoitems")
public class ToDoItemRestController {
    private final ToDoItemRepository repository;

    public ToDoItemRestController(ToDoItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ToDoItemDto> findAll() {
        Iterable<ToDoItem> found = repository.findAll();

        return asStream(found)
                .map(ToDoItem::asDto)
                .collect(toList());
    }

    private Stream<ToDoItem> asStream(Iterable<ToDoItem> found) {
        return StreamSupport.stream(found.spliterator(), false);
    }

    @GetMapping("/{id}")
    public ToDoItemDto findById(@PathVariable Long id) {
        ToDoItem toDoItem = repository.findById(id).get();

        return toDoItem.asDto();
    }

    @PostMapping
    public Long create(@RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = new ToDoItem(dto.getName(), dto.getNotes(), dto.getAssignee());
        ToDoItem saved = repository.save(toDoItem);

        return saved.getId();
    }
}
