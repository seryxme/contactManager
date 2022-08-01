package com.hotnigeria.contactManager.dtos.responses;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;

import java.util.ArrayList;
import java.util.List;

public class FindContactResponse {
    private final List<Contact> foundContacts = new ArrayList<>();

    public void addFoundContact(Contact contact){
        foundContacts.add(contact);
    }

    public List<Contact> getFoundContacts() {
        return foundContacts;
    }
}
