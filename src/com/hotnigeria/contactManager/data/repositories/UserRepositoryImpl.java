package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private ArrayList<User> users = new ArrayList<>();
    private int counter;
    @Override
    public User save(User user) {
        for(User foundUser: users) {
            if (foundUser.getUserId() == user.getUserId()) {
                foundUser = user;
                return foundUser;
            }
        }
        counter++;
        user.setUserId(counter);
        users.add(user);
        return user;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void delete(int userId) {
        User foundUser = findById(userId);
        users.remove(foundUser);
    }

    @Override
    public User findById(int userId) {
        for(User foundUser: users) {
            if (foundUser.getUserId() == userId) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public User findByFullName(String fullName) {
        for(User foundUser: users) {
            if (foundUser.getFullName().equalsIgnoreCase(fullName)) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for(User foundUser: users) {
            if (foundUser.getEmail().equalsIgnoreCase(email)) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public int count() {
        return users.size();
    }
}
