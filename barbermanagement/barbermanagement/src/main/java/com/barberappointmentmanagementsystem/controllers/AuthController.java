package com.barberappointmentmanagementsystem.controllers;

import com.barberappointmentmanagementsystem.dto.LoginDTO;
import com.barberappointmentmanagementsystem.dto.UserDTO;
import com.barberappointmentmanagementsystem.entity.User;
import com.barberappointmentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){

        User savedUser = userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully with id: "+ savedUser.getId());

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
        String token = userService.loginUser(loginDTO);
        return ResponseEntity.ok("Bearer " + token);
    }

}
