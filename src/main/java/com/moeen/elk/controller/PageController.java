package com.moeen.elk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping("/p")
    public String autoPage() {
        return "autoFill";
    }
}
