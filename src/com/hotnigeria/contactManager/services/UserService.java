package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.FindContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.AddContactResponse;
import com.hotnigeria.contactManager.dtos.responses.DeleteContactResponse;
import com.hotnigeria.contactManager.dtos.responses.FindContactResponse;
import com.hotnigeria.contactManager.dtos.responses.RegisterResponse;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest request);

    int totalUsers();
    AddContactResponse addContact(AddContactRequest addRequest);

    List<Contact> findAllUserContacts(String email);

    FindContactResponse findContactByDetail(FindContactRequest request);

    DeleteContactResponse deleteContact(DeleteContactRequest deleteRequest);
}
