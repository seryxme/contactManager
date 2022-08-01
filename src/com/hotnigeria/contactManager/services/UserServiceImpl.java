package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.ContactRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepositoryImpl;
import com.hotnigeria.contactManager.data.repositories.UserRepository;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.FindContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.AddContactResponse;
import com.hotnigeria.contactManager.dtos.responses.DeleteContactResponse;
import com.hotnigeria.contactManager.dtos.responses.FindContactResponse;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;
import com.hotnigeria.contactManager.exceptions.UserExistsException;
import com.hotnigeria.contactManager.utils.Mapper;

import java.util.List;
import java.util.Objects;

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

    @Override
    public FindContactResponse findContactByDetail(FindContactRequest request) {
        FindContactResponse foundContacts = new FindContactResponse();
        var userContacts = findAllUserContacts(request.getUserEmail());
        for(Contact contact: userContacts) {
            if (Objects.equals(contact.getFirstName(), request.getContactDetail())
            || Objects.equals(contact.getLastName(), request.getContactDetail())
            || Objects.equals(contact.getPhoneNumber(), request.getContactDetail())) {
                foundContacts.addFoundContact(contact);
            }
        }
        return foundContacts;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteRequest) {
        Contact contact = new Contact();

        Mapper.map(deleteRequest, contact);
        contactService.deleteContact(contact);

        User user = userRepository.findByEmail(deleteRequest.getUserEmail());
        user.showAllContacts().remove(contact);

        userRepository.save(user);

        return null;
    }

}
