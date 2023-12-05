//package com.example.surveybackend.security;
//// GoogleIdTokenFilter.java
//import com.example.surveybackend.model.User;
//import com.example.surveybackend.repository.UserRepository;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.Key;
//import java.util.Collections;
//
//public class GoogleIdTokenFilter extends OncePerRequestFilter {
//
//    private static final String GOOGLE_CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID"; // Replace with your actual client ID
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        String token = extractTokenFromRequest(request);
//
//        if (token != null && isValidToken(token)) {
//            // Extract user details from the token (you need to define your token structure)
//            String subject = extractSubjectFromToken(token);
//
//            // Create UserDetails or fetch from your database
//            UserDetails userDetails = createUserDetailsFromSubject(subject);
//
//            // Set Authentication in SecurityContext
//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList())
//            );
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String extractTokenFromRequest(HttpServletRequest request) {
//        // Implement logic to extract the token from the request headers, cookies, or query parameters
//        // For example, if the token is in the Authorization header:
//        String authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            return authorizationHeader.substring(7);
//        }
//        return null;
//    }
//
//    private boolean isValidToken(String token) {
//        // Implement token validation logic here
//        // For example, check the token's signature and expiration
//        try {
//            Jws<Claims> claimsJws = Jwts.parserBuilder()
//                    .setSigningKey(getSigningKey()) // Provide your secret key or public key here
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private String extractSubjectFromToken(String token) {
//        // Implement logic to extract the subject (user identifier) from the token
//        // For example, if the subject is in the "sub" claim:
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    private UserDetails createUserDetailsFromSubject(String subject) {
//        // Implement logic to create UserDetails or fetch user details from your database
//        // For example, if the subject is a user ID, fetch the user from the database
//        User user = userRepository.findById(Long.parseLong(subject))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + subject));
//
//        // Create UserDetails (adjust based on your UserDetailsService implementation)
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//    }
//
//    private Key getSigningKey() {
//        // Provide your secret key or public key here
//        // This example uses a symmetric key, but in production, you should use a secure method to load your key
//        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    }
//}
