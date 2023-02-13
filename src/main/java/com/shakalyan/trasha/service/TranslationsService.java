package com.shakalyan.trasha.service;

import com.shakalyan.trasha.dto.AddTranslationRequest;
import com.shakalyan.trasha.model.Dictionary;
import com.shakalyan.trasha.model.Translation;
import com.shakalyan.trasha.repository.DictionaryRepo;
import com.shakalyan.trasha.repository.TranslationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslationsService {

    private final TranslationRepo translationRepo;

    private final DictionaryRepo dictionaryRepo;

    public ResponseEntity<List<Translation>> getTranslationsFromDictionary(Integer userId, Integer dictionaryId) {

        Optional<Dictionary> dictionary = dictionaryRepo.findById(dictionaryId);

        if (dictionary.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!dictionary.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(new ArrayList<>(translationRepo.findAllByDictionaryId(dictionaryId)));
    }

    public ResponseEntity<Translation> addNewTranslationToDictionary(Integer userId, AddTranslationRequest request) {
        Optional<Dictionary> dictionary = dictionaryRepo.findById(request.getDictionaryId());
        if (dictionary.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!dictionary.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Translation translation = translationRepo.save(new Translation( 0,
                                                                        request.getWord(),
                                                                        request.getTranslation(),
                                                                        request.getDictionaryId()));
        return ResponseEntity.ok(translation);
    }

    @Transactional
    public ResponseEntity<String> deleteTranslationFromDictionary(Integer userId, Integer translationId) {
        Optional<Translation> translation = translationRepo.findById(translationId);

        if (translation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Dictionary dictionary = dictionaryRepo.findById(translation.get().getDictionaryId()).get();

        if (!dictionary.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        translationRepo.deleteById(translationId);
        return ResponseEntity.ok("Deleted");
    }

}
