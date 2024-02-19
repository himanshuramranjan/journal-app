package com.spring.journalApp.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String userName;
    private String password;
}
