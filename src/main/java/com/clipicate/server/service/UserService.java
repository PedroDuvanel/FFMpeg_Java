package com.clipicate.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clipicate.server.classes.User;
import com.clipicate.server.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void create(User user){
        userRepository.save(user);
    }

    public void delete(Long id){
        User user = getById(id);
        userRepository.delete(user);
    }
}
