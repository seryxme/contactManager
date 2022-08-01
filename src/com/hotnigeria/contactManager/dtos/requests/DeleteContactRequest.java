package com.hotnigeria.contactManager.dtos.requests;

import lombok.Data;

@Data
public class DeleteContactRequest {
    private int contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String userEmail;
}