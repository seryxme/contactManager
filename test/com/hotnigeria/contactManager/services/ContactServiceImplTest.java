package com.hotnigeria.contactManager.services;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {
    ContactService contactService;
    ContactRepository contactRepository;
    Contact contact;
    Contact contact1;
    Contact contact2;
    @BeforeEach
    void setUp() {
        contactRepository = new ContactRepositoryImpl();
        contactService = new ContactServiceImpl(contactRepository);
        contact = new Contact();
        contact1 = new Contact();
        contact2 = new Contact();

        contact.setEmail("newford@gmail.com");
        contact.setFirstName("New");
        contact.setLastName("Ford");
        contact.setPhoneNumber("08024533933");

        contact1.setEmail("ashford@gmail.com");
        contact1.setFirstName("Ashley");
        contact1.setLastName("Ford");
        contact1.setPhoneNumber("08024534567");

        contact2.setEmail("chetom@gmail.com");
        contact2.setFirstName("Cheryl");
        contact2.setLastName("Tom");
        contact2.setPhoneNumber("08034567933");
    }

    @Test
    public void addContactTest() {
        contactService.addNewContact(contact);

        assertEquals(1, contactService.totalContacts());
    }

}