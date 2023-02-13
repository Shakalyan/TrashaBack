package com.shakalyan.trasha.controller;

import com.shakalyan.trasha.dto.NewDictionaryRequest;
import com.shakalyan.trasha.model.Dictionary;
import com.shakalyan.trasha.repository.DictionaryRepo;
import com.shakalyan.trasha.service.AuthenticationService;
import com.shakalyan.trasha.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dictionaries")
@RequiredArgsConstructor
public class DictionariesController {

    private final DictionaryService dictionaryService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<Dictionary>> getUserDictionaries(@RequestHeader("Authorization") String token) {
        Integer userId = authenticationService.authenticate(token);
        return dictionaryService.getUserDictionaries(userId);
    }

    @PostMapping
    public ResponseEntity<Dictionary> createNewDictionary(  @RequestHeader("Authorization") String token,
                                                            @RequestBody NewDictionaryRequest request) {
        Integer userId = authenticationService.authenticate(token);
        return dictionaryService.createNewDictionary(userId, request);
    }

    @DeleteMapping
    public ResponseEntity<String> createNewDictionary(@RequestHeader("Authorization") String token,
                                                      @RequestParam("id") Integer dictionaryId) {
        Integer userId = authenticationService.authenticate(token);
        return dictionaryService.deleteDictionary(userId, dictionaryId);
    }

}
