package com.hdel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comm")
public class TestController {
    @GetMapping("/test")
    public String index() {
        int i = 1;
        i ++;

        return String.valueOf(i);
    }
}
