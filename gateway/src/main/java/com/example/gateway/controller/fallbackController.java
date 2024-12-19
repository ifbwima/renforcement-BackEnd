package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class fallbackController {

    @GetMapping("/schools")
    public String schoolServiceFallback() {
        return "School Service is not available at the moment. Please try again later.";
    }

    @GetMapping("/students")
    public String studentServiceFallback() {
        return "Student Service is not available at the moment. Please try again later.";
    }
}