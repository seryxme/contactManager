package com.hotnigeria.contactManager.controllers;


import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.FindContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.AddContactResponse;
import com.hotnigeria.contactManager.dtos.responses.DeleteContactResponse;
import com.hotnigeria.contactManager.dtos.responses.FindContactResponse;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;
import com.hotnigeria.contactManager.services.UserService;
import com.hotnigeria.contactManager.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {

    private final UserService userService = new UserServiceImpl();

    @PostMapping("/user")
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PatchMapping("/user")
    public AddContactResponse addContact(@RequestBody AddContactRequest addContactRequest){
        return userService.addContact(addContactRequest);
    }

    @GetMapping("/user/{email}")
    public List<Contact> findContactBelongingTo(@PathVariable String email){
        return userService.findAllUserContacts(email);
    }

    public FindContactResponse findContactByDetail(FindContactRequest request) {
        return userService.findContactByDetail(request);
    }

    public DeleteContactResponse deleteContact(DeleteContactRequest deleteRequest) {
        return userService.deleteContact(deleteRequest);
    }
}
