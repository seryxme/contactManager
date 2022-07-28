package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.ContactRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepository;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserService userService;
    RegisterRequest request;
    AddContactRequest addRequest;
    ContactService contactService;
    UserRepository userRepository;
    ContactRepository contactRepository;


    @BeforeEach
    void setUp() {
        contactRepository = new ContactRepositoryImpl();
        contactService = new ContactServiceImpl(contactRepository);
        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository, contactService);
        addRequest = new AddContactRequest();

        request = new RegisterRequest();
        request.setEmail("johnford@gmail.com");
        request.setFullName("John Ford");
        request.setUsername("johnford2");
        request.setPassword("johnny234");
    }

    @Test
    public void registerTest() {
        userService.register(request);

        assertEquals(1, userService.totalUsers());
    }

    @Test
    public void duplicateRegistrationThrowsException() {
        userService.register(request);

        RegisterRequest request1 = new RegisterRequest();
        request1.setEmail("johnford@gmail.com");
        request1.setFullName("Ashley Ford");
        request1.setUsername("ashford2");
        request1.setPassword("ashy1234");

        assertThrows(UserExistsException.class, () -> userService.register(request1));
        assertEquals(1, userService.totalUsers());
    }

    @Test
    public void addContactTest() {
        userService.register(request);

        addRequest.setEmail("newford@gmail.com");
        addRequest.setFirstName("New");
        addRequest.setLastName("Ford");
        addRequest.setPhoneNumber("08024533933");
        addRequest.setUserEmail(request.getEmail());
        userService.addContact(addRequest);

        assertEquals(1, userService.findAllUserContacts("johnford@gmail.com").size());
        assertEquals(1, contactService.totalContacts());
    }


}