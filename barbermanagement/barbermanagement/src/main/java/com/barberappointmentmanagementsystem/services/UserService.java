package com.barberappointmentmanagementsystem.services;

import com.barberappointmentmanagementsystem.dto.UserDTO;
import com.barberappointmentmanagementsystem.entity.User;
import com.barberappointmentmanagementsystem.enums.Role;
import com.barberappointmentmanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //we will be writing logic. so we need to inject the dependency

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //logic to register and save the new user
    public User registerUser(UserDTO userDTO){

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        //encrypt password before saving in the database
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);



        //user.setRole(Role.CUSTOMER); //using enum to set the role, this is hardcoding
        user.setRole(Role.valueOf(userDTO.getRole().toUpperCase()));

        return userRepository.save(user);

    }

}
