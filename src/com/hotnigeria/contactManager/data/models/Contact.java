package com.hotnigeria.contactManager.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Contacts")
@NoArgsConstructor
public class Contact {
    @Id
    private String contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
