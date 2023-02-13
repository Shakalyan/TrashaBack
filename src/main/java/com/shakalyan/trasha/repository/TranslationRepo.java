package com.shakalyan.trasha.repository;

import com.shakalyan.trasha.model.Translation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepo extends CrudRepository<Translation, Integer> {

    void deleteAllByDictionaryId(Integer dictionaryId);

    List<Translation> findAllByDictionaryId(Integer dictionaryId);

}
