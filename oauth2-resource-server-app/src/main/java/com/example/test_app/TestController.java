package com.example.test_app;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/")
    public String getRoot() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        Jwt jwt = (Jwt) principal;
        String subject = jwt.getSubject();
        Map<String, Object> claims = jwt.getClaims();

        return String.format("jwt: " +
                "subject (user id): %s   \n" +
                "principal claims %s ", subject, claims);
    }


    @GetMapping("/test")
    public String get() {
        return "Hello World!";
    }
}
