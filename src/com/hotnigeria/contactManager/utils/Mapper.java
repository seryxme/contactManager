package com.hotnigeria.contactManager.utils;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;

public class Mapper {
    public static void map(RegisterRequest request, User user) {
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }

    public static void map(AddContactRequest addRequest, Contact contact) {
        contact.setEmail(addRequest.getEmail());
        contact.setPhoneNumber(addRequest.getPhoneNumber());
        contact.setFirstName(addRequest.getFirstName());
        contact.setLastName(addRequest.getLastName());
    }

    public static void map(Contact contact, AddContactRequest request) {
        request.setFirstName(contact.getFirstName());
        request.setLastName(contact.getLastName());
        request.setPhoneNumber(contact.getPhoneNumber());
        request.setEmail(contact.getEmail());
    }

    public static void map(DeleteContactRequest deleteRequest, Contact contact) {
        contact.setContactId(deleteRequest.getContactId());
        contact.setEmail(deleteRequest.getEmail());
        contact.setPhoneNumber(deleteRequest.getPhoneNumber());
        contact.setFirstName(deleteRequest.getFirstName());
        contact.setLastName(deleteRequest.getLastName());
    }

    public static void map(Contact contact, DeleteContactRequest request) {
        request.setContactId(contact.getContactId());
        request.setFirstName(contact.getFirstName());
        request.setLastName(contact.getLastName());
        request.setPhoneNumber(contact.getPhoneNumber());
        request.setEmail(contact.getEmail());
    }
}
