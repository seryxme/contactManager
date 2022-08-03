package com.hotnigeria.contactManager.utils;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.models.User;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.dtos.responses.FindContactResponse;

public class Mapper {
    public static void map(RegisterRequest request, User user) {
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
    }

    public static void map(AddContactRequest addRequest, Contact contact) {
        contact.setEmail(addRequest.getEmail());
        contact.setPhoneNumber(addRequest.getPhoneNumber());
        contact.setFirstName(addRequest.getFirstName());
        contact.setLastName(addRequest.getLastName());
    }

    public static void map(FindContactResponse response, AddContactRequest request) {
        request.setFirstName(response.getFirstName());
        request.setLastName(response.getLastName());
    }

    public static void map(DeleteContactRequest deleteRequest, Contact contact) {
        contact.setContactId(deleteRequest.getContactId());
        contact.setEmail(deleteRequest.getEmail());
        contact.setPhoneNumber(deleteRequest.getPhoneNumber());
        contact.setFirstName(deleteRequest.getFirstName());
        contact.setLastName(deleteRequest.getLastName());
    }

    public static void map(FindContactResponse contact, DeleteContactRequest request) {
        request.setContactId(contact.getContactId());
        request.setFirstName(contact.getFirstName());
        request.setLastName(contact.getLastName());
    }

    public static void map(Contact contact, FindContactResponse response) {
        response.setContactId(contact.getContactId());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
    }
}
