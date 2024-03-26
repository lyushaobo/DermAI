package com.dermai.project.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityControllerTests {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Spring Security";
    }
}
