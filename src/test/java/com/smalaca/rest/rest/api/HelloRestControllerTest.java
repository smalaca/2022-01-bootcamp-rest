package com.smalaca.rest.rest.api;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class HelloRestControllerTest {
    @Test
    void shouldSayHelloWhenNameGiven() {
        RestTemplate client = new RestTemplate();

        String actual = client.getForObject("http://localhost:8013/invitations/Sebastian", String.class);

        nicePrint(actual);
    }

    @Test
    void shouldSayHelloWhenNameNotGiven() {
        RestTemplate client = new RestTemplate();

        String actual = client.getForObject("http://localhost:8013/invitations/", String.class);

        nicePrint(actual);
    }

    private void nicePrint(String actual) {
        System.out.println("---------------");
        System.out.println(actual);
        System.out.println("---------------");
    }
}