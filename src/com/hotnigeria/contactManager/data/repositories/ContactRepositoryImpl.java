package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository{
    private ArrayList<Contact> contacts = new ArrayList<>();
    private int counter;

    @Override
    public Contact save(Contact contact) {
        for(Contact contact1: contacts) {
            if (contact1.getContactId() == contact.getContactId()) {
                contact1 = contact;
                return contact1;
            }
        }
        counter++;
        contact.setContactId(counter);
        contacts.add(contact);
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void delete(int contactId) {
        Contact contact = findById(contactId);
        contacts.remove(contact);
    }

    @Override
    public Contact findById(int contactId) {
        for(Contact contact: contacts) {
            if (contact.getContactId() == contactId) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        ArrayList<Contact> foundContacts = new ArrayList<>();

        for(Contact contact: contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    @Override
    public int count() {
        return contacts.size();
    }
}
