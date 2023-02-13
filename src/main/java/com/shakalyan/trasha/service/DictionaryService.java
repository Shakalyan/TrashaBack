package com.shakalyan.trasha.service;

import com.shakalyan.trasha.dto.NewDictionaryRequest;
import com.shakalyan.trasha.model.Dictionary;
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
public class DictionaryService {

    private final DictionaryRepo dictionaryRepo;

    private final TranslationRepo translationRepo;

    public ResponseEntity<List<Dictionary>> getUserDictionaries(Integer userId) {
        return ResponseEntity.ok(new ArrayList<>(dictionaryRepo.findByUserId(userId)));
    }

    public ResponseEntity<Dictionary> createNewDictionary(Integer userId, NewDictionaryRequest request) {
        Dictionary dictionary = dictionaryRepo.save(new Dictionary( 0,
                                                                    request.getName(),
                                                                    userId,
                                                                    request.getSource(),
                                                                    request.getTarget()));
        return ResponseEntity.ok(dictionary);
    }

    @Transactional
    public ResponseEntity<String> deleteDictionary(Integer userId, Integer dictionaryId) {

        Optional<Dictionary> dictionary = dictionaryRepo.findById(dictionaryId);
        if (dictionary.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!dictionary.get().getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        translationRepo.deleteAllByDictionaryId(dictionaryId);
        dictionaryRepo.deleteById(dictionaryId);
        return ResponseEntity.ok("Dictionary deleted");
    }

}
