package com.shakalyan.trasha.service;

import com.shakalyan.trasha.dto.TranslateRequest;
import com.shakalyan.trasha.dto.TranslateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class TranslateService {

    private final String directory = "/home/shakalyan/IdeaProjects/FullTrasha";
    private final String filename = "translation_service.py";

    public ResponseEntity<TranslateResponse> translate(TranslateRequest request) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.directory(new File(directory));
        processBuilder.command("python3", filename, request.getSource(), request.getTarget(), request.getText());

        StringBuilder response = new StringBuilder();

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(new TranslateResponse(response.toString()));
    }

}
