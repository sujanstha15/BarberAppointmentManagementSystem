package com.barberappointmentmanagementsystem.services;

import com.barberappointmentmanagementsystem.dto.LoginDTO;
import com.barberappointmentmanagementsystem.dto.UserDTO;
import com.barberappointmentmanagementsystem.entity.User;
import com.barberappointmentmanagementsystem.enums.Role;
import com.barberappointmentmanagementsystem.repositories.UserRepository;
import com.barberappointmentmanagementsystem.security.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;



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

    //logic to login by valid User
    public String loginUser(LoginDTO loginDTO){
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

}
