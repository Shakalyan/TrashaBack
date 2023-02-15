package com.shakalyan.trasha.service;

import com.shakalyan.trasha.dto.AuthenticationRequest;
import com.shakalyan.trasha.dto.RegistrationRequest;
import com.shakalyan.trasha.exception.UnauthorizedException;
import com.shakalyan.trasha.model.User;
import com.shakalyan.trasha.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;

    private final HashMap<String, Integer> tokens = new HashMap<>();
    private final HashMap<Integer, String> tokensReverseMap = new HashMap<>();
    private final String tokenAlphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    private final Integer tokenLength = 64;

    public Integer getUserIdByToken(String token) {
        Integer userId = tokens.get(token);
        if (userId != null) {
            return userId;
        }
        throw new UnauthorizedException("User is not authenticated");
    }

    public ResponseEntity<String> authenticate(AuthenticationRequest request) {
        Optional<User> user = userRepo.findByLogin(request.getLogin());
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!BCrypt.checkpw(request.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = generateToken();
        Integer userId = user.get().getId();
        String oldToken = tokensReverseMap.get(userId);
        if (oldToken != null) {
            tokensReverseMap.remove(userId);
            tokens.remove(oldToken);
        }
        tokens.put(token, userId);
        tokensReverseMap.put(userId, token);

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<String> register(RegistrationRequest request) {
        Optional<User> user = userRepo.findByLogin(request.getLogin());
        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(request.getPassword(), salt);
        userRepo.save(new User(0, request.getLogin(), hashedPassword, salt));
        return ResponseEntity.ok("Registered");
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tokenLength; ++i) {
            token.append(tokenAlphabet.charAt(random.nextInt(tokenAlphabet.length())));
        }
        return token.toString();
    }

}
