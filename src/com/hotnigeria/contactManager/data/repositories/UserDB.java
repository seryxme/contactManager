package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB implements UserRepository{
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
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public int count() {
        return users.size();
    }
}
