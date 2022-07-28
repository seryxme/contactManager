package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryImplTest {
    ContactRepository contactRepo;
    Contact contact;
    Contact contact1;

    @BeforeEach
    void setUp() {
        contactRepo = new ContactRepositoryImpl();
        contact = new Contact();
        contact1 = new Contact();

        contact.setFirstName("Ade");
        contact.setLastName("Yinka");
        contact.setPhoneNumber("08023344556");
        contact.setEmail("ade@adeyinka.com");

        contact1.setFirstName("Nonso");
        contact1.setLastName("Chukwu");
        contact1.setPhoneNumber("08035445567");
        contact1.setEmail("nonso@nonsochukwu.com");

    }

    @Test
    public void contactCanBeSavedTest() {
        contactRepo.save(contact);
        assertEquals(1, contactRepo.count());
    }

    @Test
    public void contactCanBeRetrievedTest() {
        contactRepo.save(contact);
        contactRepo.save(contact1);
        assertEquals(2, contactRepo.count());

        Contact savedContact = contactRepo.findById(2);
        assertEquals("Nonso", savedContact.getFirstName());
    }

    @Test
    public void contactCanBeDeletedTest() {
        contactRepo.save(contact);
        contactRepo.save(contact1);
        assertEquals(2, contactRepo.count());

        contactRepo.delete(contact);
        Contact savedContact = contactRepo.findById(1);
        assertNull(savedContact);

        assertEquals(1, contactRepo.count());
    }

    @Test
    public void contactCanBeDeletedByIdTest() {
        contactRepo.save(contact);
        contactRepo.save(contact1);
        assertEquals(2, contactRepo.count());

        contactRepo.delete(2);
        Contact savedContact = contactRepo.findById(2);
        assertNull(savedContact);

        assertEquals(1, contactRepo.count());
    }

    @Test
    public void contactCanBeUpdatedTest() {
        contactRepo.save(contact);
        assertEquals(1, contactRepo.count());

        Contact savedContact = contactRepo.findById(1);
        assertEquals("Ade", savedContact.getFirstName());

        contact.setFirstName("Tony");
        contact.setLastName("Umez");
        contact.setPhoneNumber("08023344556");
        contact.setEmail("tony@umez.com");

        contactRepo.save(contact);
        assertEquals(1, contactRepo.count());

        Contact savedContact2 = contactRepo.findById(1);
        assertEquals("Tony", savedContact2.getFirstName());

        contactRepo.save(contact1);
        assertEquals(2, contactRepo.count());
    }
}