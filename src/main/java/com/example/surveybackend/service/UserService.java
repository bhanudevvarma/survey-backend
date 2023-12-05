//package com.example.surveybackend.service;
////
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Service;
////
////@Service
////public class UserService {
////
////    public String getCurrentUsername() {
////        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        if (principal instanceof UserDetails) {
////            return ((UserDetails) principal).getUsername();
////        }
////        return principal.toString();
////    }
////}
////
//
//import com.example.surveybackend.dto.CredentialsDto;
//import com.example.surveybackend.dto.SignUpDto;
//import com.example.surveybackend.dto.UserDto;
//import com.example.surveybackend.entites.User;
//import com.example.surveybackend.exceptions.AppException;
//import com.example.surveybackend.mappers.UserMapper;
//import com.example.surveybackend.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.nio.CharBuffer;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final UserMapper userMapper;
//
//    public UserDto login(CredentialsDto credentialsDto) {
//        User user = userRepository.findByLogin(credentialsDto.getLogin())
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//
//        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
//            return userMapper.toUserDto(user);
//        }
//        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
//    }
//
//    public UserDto register(SignUpDto userDto) {
//        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());
//
//        if (optionalUser.isPresent()) {
//            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userMapper.signUpToUser(userDto);
//        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
//
//        User savedUser = userRepository.save(user);
//
//        return userMapper.toUserDto(savedUser);
//    }
//
//    public UserDto findByLogin(String login) {
//        User user = userRepository.findByLogin(login)
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//        return userMapper.toUserDto(user);
//    }
//
//}
