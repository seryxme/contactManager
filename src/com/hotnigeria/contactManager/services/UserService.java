package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);

    int totalUsers();
}
