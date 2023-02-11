package com.shakalyan.trasha.repository;

import com.shakalyan.trasha.model.Translation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepo extends CrudRepository<Translation, Integer> {
}
