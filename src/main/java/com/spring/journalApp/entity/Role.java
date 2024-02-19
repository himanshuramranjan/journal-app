package com.spring.journalApp.entity;

import lombok.Getter;

@Getter
public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String role;
    Role(String role) {
        this.role = role;
    }
}
