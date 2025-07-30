package com.barberappointmentmanagementsystem.services;

import com.barberappointmentmanagementsystem.repositories.UserRepository;
import com.barberappointmentmanagementsystem.security.CustomUserDetails;
import com.barberappointmentmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(email);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        }


        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
