package com.example.surveybackend.controller;

import com.example.surveybackend.model.User;
import com.example.surveybackend.security.AuthResponse;
import com.example.surveybackend.service.AuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost")
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController() {
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> data) {
        try {
            String tokenId = data.get("accessToken");
            // Validate the Google token and get user information
            GoogleIdToken.Payload payload = authService.verifyGoogleToken(tokenId);

            // Create or authenticate user based on payload information
            User user = authService.createOrAuthenticateUserFromGoogle(payload);

            // Generate JWT token and send it to the client
            String jwtToken = authService.generateJwtToken(user);
            return ResponseEntity.ok(new AuthResponse(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google authentication failed");
        }
    }

    @PostMapping("/google-logins")
    public ResponseEntity<?> googleLogins(@RequestBody Map<String, String> data) {
        try {
            String tokenId = data.get("accessToken");
            // Validate the Google token and get user information
            String token = authService.authenticateWithGoogle(tokenId);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google authentication failed");
        }
    }
}


//    // Add other authentication endpoints (login, registration) as needed
//    private final UserService userService;
//    private final UserAuthenticationProvider userAuthenticationProvider;
//
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
//        UserDto userDto = userService.login(credentialsDto);
//        userDto.setToken(userAuthenticationProvider.createToken(userDto));
//        return ResponseEntity.ok(userDto);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
//        UserDto createdUser = userService.register(user);
//        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
//        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
//    }
//}
