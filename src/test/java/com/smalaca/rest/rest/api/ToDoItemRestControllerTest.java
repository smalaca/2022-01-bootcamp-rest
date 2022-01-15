package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

class ToDoItemRestControllerTest {
    private static final String URL = "http://localhost:8013/todoitems/";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnCreateToDoItem() {
        Long id = client.postForObject(URL, new ToDoItemDtoTest("Eat lunch", "not too big", "bruce banner"), Long.class);

        ToDoItemDtoTest actual = client.getForObject(URL + id, ToDoItemDtoTest.class);

        System.out.println(actual);
    }

    @Test
    void shouldReturnModifiedToDoItem() {
        Long id = client.postForObject(URL, new ToDoItemDtoTest("Talk with people", "going outside is good for you", "bruce wayne"), Long.class);
        client.put(URL + id, new ToDoItemDtoTest("not too much", "bruce banner"));

        ToDoItemDtoTest actual = client.getForObject(URL + id, ToDoItemDtoTest.class);

        System.out.println(actual);
    }

    @Test
    void shouldReturnToDoItems() {
        client.postForObject(URL, new ToDoItemDtoTest("REST testing", "use RestTemplate and Spring", "steve rogers"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Create REST resources", "@RestController - remember about annotation", "wanda maximoff"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Testing", "JUnit5", "natasha romanoff"), Long.class);

        ToDoItemDtoTest[] actual = client.getForObject(URL, ToDoItemDtoTest[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Test
    void shouldReturnToDoItemsByNameOrAssignee() {
        client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for breakfast", "clarkkent"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for lunch", "clarkkent"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for dinner", "dickgrason"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Training", "let's practice", "dickgrason"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Help people", "during night", "dickgrason"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Help people", "during night", "brucewayne"), Long.class);

        ToDoItemDtoTest[] actual = client.getForObject(URL + "?name=Eat&assignee=dickgrason", ToDoItemDtoTest[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Test
    void shouldReturnToDoItemsByIds() {
        Long id1 = client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for breakfast", "clarkkent"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for lunch", "clarkkent"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Eat", "time for dinner", "dickgrason"), Long.class);
        Long id2 = client.postForObject(URL, new ToDoItemDtoTest("Training", "let's practice", "dickgrason"), Long.class);
        client.postForObject(URL, new ToDoItemDtoTest("Help people", "during night", "dickgrason"), Long.class);
        Long id3 = client.postForObject(URL, new ToDoItemDtoTest("Help people", "during night", "brucewayne"), Long.class);

        ToDoItemDtoTest[] actual = client.getForObject(URL + "by-ids?ids=" + id1 + "," + id2 + "," + id3, ToDoItemDtoTest[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Test
    void shouldDeleteToDoItem() {
        Long id = client.postForObject(URL, new ToDoItemDtoTest("Drink water", "every day", "Aqua man"), Long.class);

        client.delete(URL + id);

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

        ToDoItemDtoTest(String notes, String assignee) {
            this.notes = notes;
            this.assignee = assignee;
        }
    }
}