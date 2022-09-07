package com.example.app.controller;

import com.example.app.DTO.UserDto;
import com.example.app.exception.UsersNotFoundException;
import com.example.app.model.User;

import com.example.app.registration.RegistrationRequest;
import com.example.app.registration.RegistrationService;
import com.example.app.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private final UsersService usersService;
   public RegistrationService registrationService;
 // public ConfirmationTokenService confirmationTokenService;


//    @GetMapping(path = "confirm")
//    public String confirm(@RequestHeader("Authorization") String token) {
//        return registrationService.confirmToken(token);
//    }

//    @GetMapping(path = "deema")
//    public String deema(@RequestHeader("Authorization") String token){
//    ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()->
//            new IllegalStateException("token not found"));
//
//        return "done";
//    }

    @GetMapping
    public String viewHomePage(){
        return "index";
    }
   @GetMapping("/Signup")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
   }

    @PostMapping("/process_register")
    public String register( RegistrationRequest request){
        registrationService.register(request);
        return "success";
    }
//  @GetMapping("/users")
//    public String getAllUsers(Model model){
//        List<User> users  = usersService.findAllUsers();
//        model.addAttribute("listUsers",users);
//        return "users";
//    }

    @GetMapping("/users/new")
    public String addUser( Model model){
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "redirect:/Signup";
    }



    @GetMapping("/users")
    public String getAllUsers(Model model,@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        List<User> users  = usersService.findAllUsers(pageNo, pageSize);
        model.addAttribute("listUsers",users);
        return "users";
    }
    @GetMapping("/users/edit/{id}")
    public String editStudentForm(@PathVariable int id , Model model) throws UsersNotFoundException {
      model.addAttribute("user",usersService.findUserById(id));
    return "edit_user";
    }
    @PostMapping("/users/{id}")
    public String updateUser( @PathVariable int id, @ModelAttribute("user") User user, Model model) throws UsersNotFoundException {
    User existingUser= usersService.findUserById(id);
    existingUser.setId(id);
    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    existingUser.setRoles(user.getRoles());
    usersService.updateUser(existingUser);

     return "redirect:/users";
    }
    @GetMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) throws UsersNotFoundException {
        usersService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/main")
    public String viewMainPage(){
        return "main";
    }
}
