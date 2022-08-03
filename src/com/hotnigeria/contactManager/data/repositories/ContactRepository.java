package com.hotnigeria.contactManager.data.repositories;

import com.hotnigeria.contactManager.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findContactByFirstName(String firstName);
}
