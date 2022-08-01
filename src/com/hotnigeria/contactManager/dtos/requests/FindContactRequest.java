package com.hotnigeria.contactManager.dtos.requests;

import lombok.Data;

@Data
public class FindContactRequest {
    private String contactDetail;
    private String userEmail;
}
