package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;

    public UserServiceImpl(UserRepository userRepository, ContactService contactService) {
        this.userRepository = userRepository;
        this.contactService = contactService;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        isExist(request);

        User user = new User();
        Mapper.map(request, user);

        userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setMessage(String.format("%s registered successfully!", request.getEmail()));

        return response;
    }

    private void isExist(RegisterRequest request) {
        User savedUser = userRepository.findUserByEmail(request.getEmail());
        if(savedUser != null) throw new UserExistsException(request.getEmail() + " already exists.");
    }


    @Override
    public int totalUsers() {
        return (int) userRepository.count();
    }

    @Override
    public AddContactResponse addContact(AddContactRequest addRequest) {
        Contact contact = new Contact();

        Mapper.map(addRequest, contact);
        Contact newContact = contactService.addNewContact(contact);

        User user = userRepository.findUserByEmail(addRequest.getUserEmail());
        user.getContacts().add(newContact);

        userRepository.save(user);

        AddContactResponse response = new AddContactResponse();
        response.setMessage(String.format("%s successfully saved!", addRequest.getFirstName()));

        return response;
    }

    @Override
    public List<FindContactResponse> findAllUserContacts(String email) {
        User user = userRepository.findUserByEmail(email);
        List<Contact> contactsList = user.getContacts();
        List<FindContactResponse> responseList = new ArrayList<>();
        for (Contact contact: contactsList) {
            FindContactResponse response = new FindContactResponse();
            Mapper.map(contact, response);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public List<FindContactResponse> findContactByDetail(FindContactRequest request) {
        FindContactResponse foundContacts = new FindContactResponse();
        List<FindContactResponse> userContacts = findAllUserContacts(request.getUserEmail());
        List<FindContactResponse> responseList = new ArrayList<>();
        for(FindContactResponse response : userContacts) {
            if (Objects.equals(response.getFirstName(), request.getContactDetail())
            || Objects.equals(response.getLastName(), request.getContactDetail())) {
                responseList.add(response);
            }
        }
        return responseList;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest deleteRequest) {
        Contact contact = new Contact();

        Mapper.map(deleteRequest, contact);
        contactService.deleteContact(contact);

        User user = userRepository.findUserByEmail(deleteRequest.getUserEmail());
        user.getContacts().remove(contact);

        userRepository.save(user);

        return null;
    }

}
