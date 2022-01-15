package com.smalaca.rest.rest.api;

import com.smalaca.rest.domain.todoitem.ToDoItem;
import com.smalaca.rest.domain.todoitem.ToDoItemDto;
import com.smalaca.rest.domain.todoitem.ToDoItemRepository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/by-ids")
    public List<ToDoItemDto> findAll(@RequestParam MultiValueMap<String, String> params) {
        List<Long> ids = params.get("ids")
                .stream()
                .map(Long::parseLong)
                .collect(toList());
        List<ToDoItem> found = repository.findAllByIdIn(ids);

        return found.stream()
                .map(ToDoItem::asDto)
                .collect(toList());
    }

    @GetMapping
    public List<ToDoItemDto> findAll(@RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            Iterable<ToDoItem> found = repository.findAll();

            return asStream(found)
                    .map(ToDoItem::asDto)
                    .collect(toList());
        } else {
            List<ToDoItem> found = repository.findAllByNameOrAssignee(params.get("name"), params.get("assignee"));

            return found.stream()
                    .map(ToDoItem::asDto)
                    .collect(toList());
        }
    }

    private Stream<ToDoItem> asStream(Iterable<ToDoItem> found) {
        return StreamSupport.stream(found.spliterator(), false);
    }

    @PostMapping
    public Long create(@RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = new ToDoItem(dto.getName(), dto.getNotes(), dto.getAssignee());
        ToDoItem saved = repository.save(toDoItem);

        return saved.getId();
    }

    @GetMapping("/{id}")
    public ToDoItemDto findById(@PathVariable Long id) {
        ToDoItem toDoItem = repository.findById(id).get();

        return toDoItem.asDto();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public void modify(@PathVariable Long id, @RequestBody ToDoItemDto dto) {
        ToDoItem toDoItem = repository.findById(id).get();
        toDoItem.update(dto);

        repository.save(toDoItem);
    }
}
