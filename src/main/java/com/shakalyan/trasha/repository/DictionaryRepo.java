package com.shakalyan.trasha.repository;

import com.shakalyan.trasha.model.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepo extends CrudRepository<Dictionary, Integer> {

    List<Dictionary> findByUserId(Integer userId);

}
