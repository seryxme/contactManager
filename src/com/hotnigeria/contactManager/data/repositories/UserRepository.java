package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    void delete(User user);
    void delete(int userId);
    User findById(int userId);
    User findByFullName(String fullName);
    User findByEmail(String email);
    List<User> findAllUsers();
    int count();
}
