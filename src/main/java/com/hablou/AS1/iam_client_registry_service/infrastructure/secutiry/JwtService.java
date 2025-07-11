package com.hablou.AS1.iam_client_registry_service.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "chaveSuperSecreta123456";
    private static final long EXPIRATION = 86400000; // 1 dia

    public String generateTokenClient(String clientId) {
        return Jwts.builder()
                .setSubject(clientId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
