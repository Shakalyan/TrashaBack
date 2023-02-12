package com.shakalyan.trasha.service;

import com.shakalyan.trasha.model.Dictionary;
import com.shakalyan.trasha.repository.DictionaryRepo;
import com.shakalyan.trasha.repository.TranslationRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepo dictionaryRepo;

    private final TranslationRepo translationRepo;

    public ResponseEntity<List<Dictionary>> getUserDictionaries(Integer userId) {
        return ResponseEntity.ok(new ArrayList<>(dictionaryRepo.findByUserId(userId)));
    }

    public ResponseEntity<Dictionary> createNewDictionary(Integer userId, String name) {
        Dictionary dictionary = dictionaryRepo.save(new Dictionary(0, name, userId));
        return ResponseEntity.ok(dictionary);
    }

    @Transactional
    public ResponseEntity<String> deleteDictionary(Integer dictionaryId) {
        translationRepo.deleteAllByDictionaryId(dictionaryId);
        dictionaryRepo.deleteById(dictionaryId);
        return ResponseEntity.ok("Dictionary deleted");
    }

}
