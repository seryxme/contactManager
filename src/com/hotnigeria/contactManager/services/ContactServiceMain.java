package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;

public class ContactServiceMain implements ContactService {

    @Override
    public Contact addNewContact(Contact contact) {
        return new Contact();
    }
}
