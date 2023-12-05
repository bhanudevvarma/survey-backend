package com.example.surveybackend.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;

public class KeyGen {

        public static void main(String[] args) {
            // Generate a secure secret key
//            Key secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//
//                    // Print the encoded form of the key
//            System.out.println("Encoded Key: " + secretKey.getEncoded());

            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String secretString =  Encoders.BASE64.encode(key.getEncoded());
            System.out.println("Kex: " + secretString);
        }
    }

