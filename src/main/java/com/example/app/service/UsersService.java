package com.example.app.service;

import com.example.app.DTO.UserDto;
import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.exception.UsersNotFoundException;
import com.example.app.model.Invoice;
import com.example.app.model.User;

import com.example.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {
    private final static String User_Not_Found = "user with this email %s not found";
    @Autowired
    private final UserRepository userRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //add and Save
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        return userRepository.save(user);
    }

   //  find all invoices
    public List<User> findAllUsers(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult.toList();
    }
//    public List<User> findAllUsers(){
//        return (List<User>) userRepository.findAll();
//    }

    //update
    public User updateUser(User user) {

        return userRepository.save(user);

    }


    public void deleteUserById(Integer id) throws UsersNotFoundException {

        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            userRepository.deleteById(id);
            ;
        } else {
            throw new UsersNotFoundException("Could not find any user with ID " + id);

        }
    }

    public User findUserById(Integer id) throws UsersNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UsersNotFoundException("Could not find any user with ID " + id);
    }



    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
       userRepository.save(user);
       return "success";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(User_Not_Found, email)));
    }
}
