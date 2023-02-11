package com.shakalyan.trasha.controller;

import com.shakalyan.trasha.model.Dictionary;
import com.shakalyan.trasha.repository.DictionaryRepo;
import com.shakalyan.trasha.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dictionaries")
@RequiredArgsConstructor
public class DictionariesController {

    private final DictionaryRepo dictionaryRepo;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<Dictionary>> getUserDictionaries(@RequestHeader("Authorization") String token) {
        Integer userId = authenticationService.authenticate(token);
        List<Dictionary> response = new ArrayList<>(dictionaryRepo.findByUserId(userId));
        return ResponseEntity.ok(response);
    }

}
