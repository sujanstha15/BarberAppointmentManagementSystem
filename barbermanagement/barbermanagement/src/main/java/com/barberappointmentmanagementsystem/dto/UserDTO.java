package com.barberappointmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Data will generates getters, setters and toString
public class UserDTO {
    private String name;
    private String email;
    private String password; //only needed during registration
    private String role;
}
