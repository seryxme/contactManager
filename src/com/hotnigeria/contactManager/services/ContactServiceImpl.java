package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
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
        return (int)contactRepository.count();
    }

    @Override
    public List<Contact> findContactByFirstName(String firstName) {
        return contactRepository.findContactByFirstName(firstName);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }
}
