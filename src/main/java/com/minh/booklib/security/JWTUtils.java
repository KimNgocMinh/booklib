package com.minh.booklib.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class JWTUtils {



    private final static String SECRET = "dj239qJEO#(J93JE@J)EP@J.3231SAq4)@U";

    private final static Long EXPIRED_TIME = 3600L;
    public static String verifyJWT(String token) {
        String author = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("Minh").build();
            DecodedJWT jwt = verifier.verify(token);
            author = jwt.getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return author;
    }

    public static String generateToken(String username) {

        String token = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Instant expireAt = Instant.now().plusSeconds(EXPIRED_TIME);

            token = JWT.create()
                    .withIssuer("Minh")
                    .withSubject(username)
                    .withExpiresAt(expireAt)
                    .withIssuedAt(Instant.now())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }

        return token;
    }
}
