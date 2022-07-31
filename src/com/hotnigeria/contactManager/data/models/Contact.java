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
        return String.format("""
                
                First Name: %s
                Last Name: %s
                Phone Number: %s
                Email: %s
                """, firstName, lastName, phoneNumber, email);
    }
}
