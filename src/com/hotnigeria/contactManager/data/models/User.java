package com.hotnigeria.contactManager.data.models;

import com.hotnigeria.contactManager.data.repositories.ContactRepository;
import com.hotnigeria.contactManager.data.repositories.ContactSaver;

import java.util.List;

public class User {
    private int userId;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private ContactRepository contacts = new ContactSaver();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addContact(Contact contact) {
        contacts.save(contact);
    }

    public void deleteContact(Contact contact) {
        contacts.delete(contact);
    }

    public List<Contact> findContact(String name) {
        return contacts.findByFirstName(name);
    }

    public List<Contact> showAllContacts() {
        return contacts.findAll();
    }
}
