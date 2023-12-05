package com.example.surveybackend.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Customize user details if necessary
        // ...
//        CustomOAuth2User customOAuth2User = customizeUserDetails(oAuth2User);
//
//        return customOAuth2User;
        System.out.println(oAuth2User.getAttributes());
        return oAuth2User;
    }

//    private CustomOAuth2User customizeUserDetails(OAuth2User oAuth2User) {
//        // Extract necessary details from oAuth2User
//        String email = oAuth2User.getAttribute("email");
//        String name = oAuth2User.getAttribute("name");
//
//        // Perform custom logic to modify or enhance user details
//        // For example, you can check if the user already exists in your database
//        // If not, create a new user with the extracted details
//
//        // Customize the user attributes
//        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
//        attributes.put("customAttribute", "someCustomValue");
//
//        // Create a new OAuth2User with customized attributes
//        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, "sub");
//    }

    public OAuth2User loadUserFromGoogle(String accessToken) {
        // Manually load user details from Google using the provided access token
        // ...

        return new DefaultOAuth2User(Collections.emptyList(), Map.of(), "sub");
    }
}
