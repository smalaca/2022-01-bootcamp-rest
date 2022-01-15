package com.smalaca.rest.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invitations")
public class HelloRestController {
    @GetMapping
    public String sayHi() {
        return "Hi!";
    }
}
