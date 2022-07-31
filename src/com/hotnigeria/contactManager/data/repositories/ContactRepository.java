package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;

import java.util.List;

public interface ContactRepository {
    Contact save(Contact contact);

    void delete(Contact contact);
    void delete(int contactId);
    Contact findById(int Id);
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByLastName(String lastName);
    List<Contact> findAll();
    int count();
}
