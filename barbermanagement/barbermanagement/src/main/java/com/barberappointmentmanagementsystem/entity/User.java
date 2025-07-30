package com.barberappointmentmanagementsystem.entity;

import com.barberappointmentmanagementsystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data //created setter and getter by lombok
@NoArgsConstructor //created no argument constructor by lombok
@AllArgsConstructor //creates constructor with argument by lombok
public class User {
    //instance variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) //this will save the enum value as string in database
    private Role role; //either CUSTOMER or BARBER
}
