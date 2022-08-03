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
import com.hotnigeria.contactManager.exceptions.UserExistsException;
import com.hotnigeria.contactManager.services.UserService;
import com.hotnigeria.contactManager.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
            return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
        } catch (UserExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user")
    public AddContactResponse addContact(@RequestBody AddContactRequest addContactRequest){
        return userService.addContact(addContactRequest);
    }

    @GetMapping("/user/{email}")
    public List<FindContactResponse> findContactBelongingTo(@PathVariable String email){
        return userService.findAllUserContacts(email);
    }

    @PostMapping("/contacts")
    public List<FindContactResponse> findContactByDetail(FindContactRequest request) {
        return userService.findContactByDetail(request);
    }

    @DeleteMapping("/user")
    public DeleteContactResponse deleteContact(@RequestBody DeleteContactRequest deleteRequest) {
        return userService.deleteContact(deleteRequest);
    }
}
