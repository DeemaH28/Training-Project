package com.example.app.model;

public enum Role{
    SupportUser("SupportUser"),
    SuperUser ("SuperUser"),
    Auditor ("Auditor");

    private final String name;

    Role(String name) {

        this.name = name;
    }
}