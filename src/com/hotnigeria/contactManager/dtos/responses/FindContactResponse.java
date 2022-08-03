package com.hotnigeria.contactManager.dtos.responses;

import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FindContactResponse {
    private String contactId;
    private String firstName;
    private String lastName;
}
