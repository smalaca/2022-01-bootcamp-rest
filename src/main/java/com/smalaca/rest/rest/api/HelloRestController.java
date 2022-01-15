package com.smalaca.rest.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/invitations")
public class HelloRestController {
    @GetMapping({"/", "/{name}"})
    public String sayHi(@PathVariable Optional<String> name) {
        return "Hi " + name.orElse("you") + "!";
    }
}
