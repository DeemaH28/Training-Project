package com.example.app.registration;

import com.example.app.model.Role;
import com.example.app.model.User;

import com.example.app.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

//    public String register(RegistrationRequest request) {
//        return "works";
//    }
    private final UsersService usersService;
    private EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if (!isValidEmail){
          throw  new IllegalStateException("email not valid");
       //     return "error";
        }

        return usersService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getRoles()

                )
        );
    }


}
