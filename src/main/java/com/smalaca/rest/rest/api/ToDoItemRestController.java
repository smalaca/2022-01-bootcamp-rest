package com.smalaca.rest.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/todoitems")
public class ToDoItemRestController {
    @GetMapping
    public List<ToDoItemDto> findAll() {
        return Arrays.asList(
                new ToDoItemDto(1L, "no more failures", "let my PC work", "smalaca"),
                new ToDoItemDto(2L, "save NY", "do it as soon as possible", "peterparker"),
                new ToDoItemDto(3L, "get rid of half of the universe", "N/A", "thanos")
        );
    }
}
