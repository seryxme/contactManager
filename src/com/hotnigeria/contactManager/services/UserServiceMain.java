package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.User;
import com.hotnigeria.contactManager.data.repositories.UserDB;
import com.hotnigeria.contactManager.data.repositories.UserRepository;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;
import com.hotnigeria.contactManager.exceptions.UserExistsException;

public class UserServiceMain implements UserService {

    private UserRepository userRepository = new UserDB();
    @Override
    public RegisterResponse register(RegisterRequest request) {

        User savedUser = userRepository.findByEmail(request.getEmail());
        if(savedUser != null) throw new UserExistsException(request.getEmail() + " already exists.");

        User user = new User();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setMessage("Registration successful!");

        return response;
    }

    @Override
    public int totalUsers() {
        return userRepository.count();
    }
}
