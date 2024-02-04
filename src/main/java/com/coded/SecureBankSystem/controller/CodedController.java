package com.coded.SecureBankSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CodedController {
    @GetMapping("/sayHi")
    public String sayHi() {
        return "welcome to Coded";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello, " + name;
    }

    @PostMapping("/farewell")
    public String farewell(@RequestBody FarewellRequest farewellRequest) {
        String name = farewellRequest.getName();
        return "Goodbye, " + name;
    }
}








