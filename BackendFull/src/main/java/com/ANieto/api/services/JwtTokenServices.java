/*
package com.ANieto.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenServices {
    private final Algorithm hmac512;
    private final JWTVerifier jwtVerifier;
    private final int JWT_TOKEN_VALIDITY=8400;
    public JwtTokenServices(@Value("${jwt.secret}") final String secret){
        this.hmac512 =Algorithm.HMAC512(secret);
        this.jwtVerifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .sign(this.hmac512);
    }

    public String ValidateTokenAndGetUsername(final String token){
        try{
            return jwtVerifier.verify(token).getSubject();
        }
        catch (final JWTVerificationException verificationException){
            return null;
        }
    }
}
*/