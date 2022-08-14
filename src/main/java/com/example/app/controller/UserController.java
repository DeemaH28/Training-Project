package com.example.app.controller;


import com.example.app.exception.UsersNotFoundException;
import com.example.app.model.Users;
import com.example.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> users  = usersService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Users> getAllUserById(@PathVariable("id") Integer id) throws UsersNotFoundException {
       Users user = usersService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users newUser = usersService.addUser(users);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users users){
        Users updateUser = usersService.updateUser(users);
        return new ResponseEntity<>(updateUser,  HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) throws UsersNotFoundException {
        usersService.deleteUserById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
