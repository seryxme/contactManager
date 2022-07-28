package com.hotnigeria.contactManager.controllers;


import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.AddContactResponse;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;
import com.hotnigeria.contactManager.services.UserService;
import com.hotnigeria.contactManager.services.UserServiceImpl;

import java.util.List;

public class UserController {

    private UserService userService = new UserServiceImpl();

    public RegisterResponse registerUser(RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    public AddContactResponse addContact(AddContactRequest addContactRequest){
        return userService.addContact(addContactRequest);
    }

    public List<Contact> findContactBelongingTo(String email){
        return userService.findAllUserContacts(email);
    }
}
