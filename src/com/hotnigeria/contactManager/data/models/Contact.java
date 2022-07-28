package com.hotnigeria.contactManager.data.models;

import lombok.Data;

@Data
public class Contact {
    private int contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
