package com.example.surveybackend.model;


import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    // Add other user details as needed
    // ...

    // Getters and setters
}
