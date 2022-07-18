package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    void delete(User user);
    void delete(int userId);
    User findById(int userId);
    User findByUsername(String username);
    List<User> findAllUsers();
    int count();
}
