package com.shakalyan.trasha.service;

import com.shakalyan.trasha.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private final HashMap<String, Integer> tokens = new HashMap<>();

    public AuthenticationService() {
        this.tokens.put("123", 1);
    }

    public Integer authenticate(String token) {
        Integer userId = tokens.get(token);
        if (userId != null) {
            return userId;
        }
        throw new UnauthorizedException("User is not authenticated");
    }

}
