package com.example.test_app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

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

    @GetMapping(value = "/jwt", produces = "application/json")
    public String jwt() throws JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        Jwt jwt = (Jwt) principal;
        String s = objectMapper.writeValueAsString(jwt);

        return s;
    }


    @GetMapping("/test")
    public String get() {
        return "Hello World!";
    }
}
