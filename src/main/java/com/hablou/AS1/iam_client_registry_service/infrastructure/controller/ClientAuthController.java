package com.hablou.AS1.iam_client_registry_service.infrastructure.controller;

import com.hablou.AS1.iam_client_registry_service.application.service.ClientAuthService;
import com.hablou.AS1.iam_client_registry_service.infrastructure.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class ClientAuthController {

    private final ClientAuthService clientAuthService;
    private final JwtService jwtService;

    public ClientAuthController(ClientAuthService clientAuthService, JwtService jwtService) {
        this.clientAuthService = clientAuthService;
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(HttpServletRequest request) {
        String clientId = request.getHeader("clientId");
        String clientSecret = request.getHeader("clientSecret");
        String apiKey = request.getHeader("apiKey");

        if (clientId == null || clientSecret == null || apiKey == null) {
            return ResponseEntity.status(401).body("Missing authentication headers");
        }

        if (!clientAuthService.authenticate(clientId, clientSecret, apiKey)) {
            return ResponseEntity.status(401).body("Invalid client credentials");
        }

        String token = jwtService.generateTokenClient(clientId);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    private static class TokenResponse {
        public String token;

        public TokenResponse(String token) {
            this.token = token;
        }
    }
}