package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public int totalContacts() {
        return contactRepository.count();
    }

    @Override
    public List<Contact> findContactByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact.getContactId());
    }
}
