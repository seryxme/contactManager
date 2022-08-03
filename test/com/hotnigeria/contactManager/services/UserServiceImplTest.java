package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.UserRepository;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.FindContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.exceptions.UserExistsException;
import com.hotnigeria.contactManager.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserService userService;
    RegisterRequest request;
    AddContactRequest addRequest;
    AddContactRequest addRequest1;
    AddContactRequest addRequest2;
    FindContactRequest findRequest;
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
        addRequest1 = new AddContactRequest();
        addRequest2 = new AddContactRequest();
        findRequest = new FindContactRequest();

        request = new RegisterRequest();
        request.setEmail("johnford@gmail.com");
        request.setFullName("John Ford");
        request.setUsername("johnford2");
        request.setPassword("johnny234");

        addRequest.setEmail("newford@gmail.com");
        addRequest.setFirstName("New");
        addRequest.setLastName("Ford");
        addRequest.setPhoneNumber("08024533933");
        addRequest.setUserEmail(request.getEmail());

        addRequest1.setEmail("ashford@gmail.com");
        addRequest1.setFirstName("Ashley");
        addRequest1.setLastName("Ford");
        addRequest1.setPhoneNumber("08024534567");
        addRequest1.setUserEmail(request.getEmail());

        addRequest2.setEmail("chetom@gmail.com");
        addRequest2.setFirstName("Cheryl");
        addRequest2.setLastName("Tom");
        addRequest2.setPhoneNumber("08034567933");
        addRequest2.setUserEmail(request.getEmail());
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

        userService.addContact(addRequest);

        assertEquals(1, userService.findAllUserContacts("johnford@gmail.com").size());
        assertEquals(1, contactService.totalContacts());
    }

    @Test
    public void findContactByFirstNameReturnIdTest() {
        userService.register(request);

        userService.addContact(addRequest);
        userService.addContact(addRequest1);
        userService.addContact(addRequest2);

        findRequest.setUserEmail(request.getEmail());
        findRequest.setContactDetail("Ashley");

        assertEquals(3, contactService.totalContacts());
        assertEquals(2, userService.findContactByDetail(findRequest).getFoundContacts().get(0).getContactId());
    }

    @Test
    public void deleteContactByFirstNameReturnIdTest() {
        userService.register(request);

        userService.addContact(addRequest);
        userService.addContact(addRequest1);
        userService.addContact(addRequest2);

        findRequest.setUserEmail(request.getEmail());
        findRequest.setContactDetail("Ashley");

        Contact contact = userService.findContactByDetail(findRequest).getFoundContacts().get(0);
        DeleteContactRequest deleteRequest = new DeleteContactRequest();
        Mapper.map(contact, deleteRequest);
        deleteRequest.setUserEmail(request.getEmail());
        userService.deleteContact(deleteRequest);

        assertEquals(2, contactService.totalContacts());
        assertEquals(2, userService.findAllUserContacts("johnford@gmail.com").size());
    }
}