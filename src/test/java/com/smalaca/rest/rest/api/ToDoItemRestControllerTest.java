package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoItemRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitems";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnToDoItems() {
        client.postForObject(URL, new ToDoItemDtoTest("REST testing", "use RestTemplate and Spring", "steve rogers"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Create REST resources", "@RestController - remember about annotation", "wanda maximoff"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Testing", "JUnit5", "natasha romanoff"), Long.class);

        ToDoItemDtoTest[] actual = client.getForObject(URL, ToDoItemDtoTest[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class ToDoItemDtoTest {
        private Long id;
        private String name;
        private String notes;
        private String assignee;

        ToDoItemDtoTest(String name, String notes, String assignee) {
            this.name = name;
            this.notes = notes;
            this.assignee = assignee;
        }
    }
}