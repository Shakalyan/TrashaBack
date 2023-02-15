package com.shakalyan.trasha.controller;

import com.shakalyan.trasha.dto.AddTranslationRequest;
import com.shakalyan.trasha.model.Translation;
import com.shakalyan.trasha.service.AuthenticationService;
import com.shakalyan.trasha.service.TranslationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/translations")
@RequiredArgsConstructor
public class TranslationsController {


    private final AuthenticationService authenticationService;

    private final TranslationsService translationsService;


    @GetMapping
    public ResponseEntity<List<Translation>> getAllTranslationsFromDictionary(@RequestHeader("Authorization") String token,
                                                                              @RequestParam("id") Integer dictionaryId) {
        Integer userId = authenticationService.getUserIdByToken(token);
        return translationsService.getTranslationsFromDictionary(userId, dictionaryId);
    }

    @PostMapping
    public ResponseEntity<Translation> addNewTranslationToDictionary(@RequestHeader("Authorization") String token,
                                                                     @RequestBody AddTranslationRequest request) {
        Integer userId = authenticationService.getUserIdByToken(token);
        return translationsService.addNewTranslationToDictionary(userId, request);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTranslationFromDictionary(@RequestHeader("Authorization") String token,
                                                                  @RequestParam("id") Integer translationId) {
        Integer userId = authenticationService.getUserIdByToken(token);
        return translationsService.deleteTranslationFromDictionary(userId, translationId);
    }


}
