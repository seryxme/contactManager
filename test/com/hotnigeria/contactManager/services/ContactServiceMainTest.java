package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.ContactRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceMainTest {
    ContactService contactService;
    ContactRepository contactRepository;
    Contact contact;
    @BeforeEach
    void setUp() {
        contactRepository = new ContactRepositoryImpl();
        contactService = new ContactServiceImpl(contactRepository);
        contact = new Contact();

        contact.setEmail("newford@gmail.com");
        contact.setFirstName("New");
        contact.setLastName("Ford");
        contact.setPhoneNumber("08024533933");
    }

    @Test
    public void addContactTest() {
        contactService.addNewContact(contact);

        assertEquals(1, contactService.totalContacts());
    }

}