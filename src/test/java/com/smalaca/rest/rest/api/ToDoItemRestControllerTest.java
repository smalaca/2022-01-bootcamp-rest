package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoItemRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitems";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnToDoItems() {
        ToDoItemDtoTest[] actual = client.getForObject(URL, ToDoItemDtoTest[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Getter
    @ToString
    public static class ToDoItemDtoTest {
        private Long id;
        private String name;
        private String notes;
        private String assignee;
    }
}