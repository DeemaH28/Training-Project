package com.example.app.model;

public enum Role{
    SupportUser("SupportUser"),
    Superuser ("Superuser"),
    AuditorRoles ("AuditorRoles");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}