package com.smalaca.rest.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class RestResourceController {
    @GetMapping
    public String isFun() {
        return "REST is fun!";
    }

    @GetMapping({"/lucky-number/{login}/{luckyNumber}", "/lucky-number/{login}"})
    public String luckyNumber(@PathVariable String login, @PathVariable Optional<Integer> luckyNumber) {
        return "Cześć, jestem " + login + ". Moja szczęśliwa liczba to: " + luckyNumber.orElse(13);
    }
}
