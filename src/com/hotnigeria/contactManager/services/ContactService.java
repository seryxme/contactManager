package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;

import java.util.List;

public interface ContactService {

    Contact addNewContact(Contact contact);

    int totalContacts();

    List<Contact> findContactByFirstName(String firstName);

    void deleteContact(Contact contact);
}
