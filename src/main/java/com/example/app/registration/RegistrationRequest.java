package com.example.app.registration;

import com.example.app.model.Role;
import lombok.*;

import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;

    private final String password;
    private final String email;
    private final String userName;
    private final Role roles;

}


