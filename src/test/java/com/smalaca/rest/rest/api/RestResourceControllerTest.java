package com.smalaca.rest.rest.api;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RestResourceControllerTest {
    private static final String REST_RESOURCE = "http://localhost:8013/rest";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnRestIsFun() {
        String actual = client.getForObject(REST_RESOURCE, String.class);

        System.out.println(actual);
    }

    @Test
    void shouldReturnLuckyNumberWhenGiven() {
        String actual = client.getForObject(REST_RESOURCE + "/lucky-number/smalaca/42", String.class);

        System.out.println(actual);
    }

    @Test
    void shouldReturnDefaultLuckyNumber() {
        String actual = client.getForObject(REST_RESOURCE + "/lucky-number/smalaca/", String.class);

        System.out.println(actual);
    }
}