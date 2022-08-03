package com.hotnigeria.contactManager.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Users")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;
    @DBRef
    private List<Contact> contacts = new ArrayList<>();
}


