package com.example.app.service;

import com.example.app.exception.UsersNotFoundException;
import com.example.app.model.Users;
import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsersService {
    @Autowired
    private final UserRepository userRepository;

    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //add and Save
    public Users addUser(Users users){
        return userRepository.save(users);
    }

    // find all invoices
    public List<Users> findAllUsers(){
        return (List<Users>) userRepository.findAll();
    }
    //update
    public Users updateUser(Users users){
        return userRepository.save(users);
    }

    public void deleteUserById(Integer id)throws UsersNotFoundException {

        Long count = userRepository.countById(id);
        if (count == null || count == 0){
            throw new UsersNotFoundException("Could not find any user with ID "+ id);
        }
        userRepository.deleteById(id);
    }

    public Users findUserById(Integer id) throws UsersNotFoundException{
        Optional<Users> result = userRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new UsersNotFoundException("Could not find any user with ID "+ id);
    }
}
