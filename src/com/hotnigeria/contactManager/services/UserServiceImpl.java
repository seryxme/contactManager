package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.ContactRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepository;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.AddContactResponse;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;
import com.hotnigeria.contactManager.exceptions.UserExistsException;
import com.hotnigeria.contactManager.utils.Mapper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ContactService contactService;

    public UserServiceImpl(UserRepository userRepository, ContactService contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }
    public UserServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
        ContactRepository contactRepository = new ContactRepositoryImpl();
        this.contactService = new ContactServiceImpl(contactRepository);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        isExist(request);

        User user = new User();
        Mapper.map(request, user);

        userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setMessage("Registration successful!");

        return response;
    }

    private void isExist(RegisterRequest request) {
        User savedUser = userRepository.findByEmail(request.getEmail());
        if(savedUser != null) throw new UserExistsException(request.getEmail() + " already exists.");
    }


    @Override
    public int totalUsers() {
        return userRepository.count();
    }

    @Override
    public AddContactResponse addContact(AddContactRequest addRequest) {
        Contact contact = new Contact();

        Mapper.map(addRequest, contact);
        Contact newContact = contactService.addNewContact(contact);

        User user = userRepository.findByEmail(addRequest.getUserEmail());
        user.showAllContacts().add(newContact);

        userRepository.save(user);

        return null;
    }

    @Override
    public List<Contact> findAllUserContacts(String email) {
        User user = userRepository.findByEmail(email);
        return user.showAllContacts();
    }
}
