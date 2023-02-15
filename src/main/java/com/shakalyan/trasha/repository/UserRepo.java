package com.shakalyan.trasha.repository;

import com.shakalyan.trasha.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);

}
