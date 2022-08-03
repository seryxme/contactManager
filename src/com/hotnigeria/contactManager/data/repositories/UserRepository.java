package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByEmail(String email);
}
