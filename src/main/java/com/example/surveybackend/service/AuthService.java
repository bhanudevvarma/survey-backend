package com.example.surveybackend.service;

import com.example.surveybackend.model.User;
import com.example.surveybackend.model.Users;
import com.google.api.client.json.JsonFactory;
import com.example.surveybackend.repository.UserRepository;
import com.example.surveybackend.security.JwtTokenProvider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UsersRepository usersRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

   // testing
   @Autowired
   private CustomOAuth2UserService customOAuth2UserService;

    public GoogleIdToken.Payload verifyGoogleToken(String tokenId) throws GeneralSecurityException, IOException, java.io.IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("567556241691-iecfqmec78tsm9cj7j63f37pl79qjjuk.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(tokenId);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new RuntimeException("Invalid Google token");
        }
    }

    public User createOrAuthenticateUserFromGoogle(GoogleIdToken.Payload payload) {
        // Extract user information from the payload and check if the user exists in your database
        // If the user exists, authenticate them; if not, create a new user
        // Example: (Note: You might want to handle this more securely, possibly using OAuth)
        String email = payload.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            // Set other user details as needed
            userRepository.save(newUser);
            return newUser;
        }
    }

    public String generateJwtToken(User user) {
        return jwtTokenProvider.generateToken(user.getEmail());
    }

    // Add other authentication-related methods as needed

    public String authenticateWithGoogle(String accessToken) {
        OAuth2User oAuth2User = customOAuth2UserService.loadUserFromGoogle(accessToken);

        // Perform user creation or update based on oAuth2User details
        User users = createUserIfNeeded(oAuth2User);

        // Generate a JWT token
        String token = jwtTokenProvider.generateToken(users);

        return token;
    }

    private User createUserIfNeeded(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            // User already exists, update details if needed
            User users = existingUser.get();
            updateUserDetails(users, oAuth2User);
            return userRepository.save(users);
        } else {
            // User does not exist, create a new user
            User newUser = createNewUser(oAuth2User);
            return userRepository.save(newUser);
        }
    }

    private User createNewUser(OAuth2User oAuth2User) {
        // Create a new user based on oAuth2User attributes
        User users = new User();
        users.setEmail(oAuth2User.getAttribute("email"));

        return users;
    }

    private void updateUserDetails(User users, OAuth2User oAuth2User) {
        users.setEmail(oAuth2User.getAttribute("email"));
    }
}
