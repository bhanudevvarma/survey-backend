package com.example.surveybackend.repository;

import com.example.surveybackend.model.User;
import com.example.surveybackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
//    Optional<Users> findByEmail(String email);
}
//
//import com.example.surveybackend.entites.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Optional<User> findByLogin(String login);
//}