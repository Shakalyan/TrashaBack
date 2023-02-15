package com.shakalyan.trasha.controller;

import com.shakalyan.trasha.dto.TranslateRequest;
import com.shakalyan.trasha.dto.TranslateResponse;
import com.shakalyan.trasha.service.AuthenticationService;
import com.shakalyan.trasha.service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
@RequiredArgsConstructor
public class TranslateController {

    private final AuthenticationService authenticationService;

    private final TranslateService translateService;

    @PostMapping
    public ResponseEntity<TranslateResponse> translate( @RequestHeader("Authorization") String token,
                                                        @RequestBody TranslateRequest request) {
        authenticationService.getUserIdByToken(token);
        return translateService.translate(request);
    }

}
