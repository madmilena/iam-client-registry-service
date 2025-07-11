package com.hablou.AS1.iam_client_registry_service.application.service;

import com.hablou.AS1.iam_client_registry_service.infrastructure.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthService {

    private final ClientRepository clientRepository;

    public ClientAuthService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean authenticate(String clientId, String clientSecret, String apiKey) {
        return clientRepository.findByClientIdAndClientSecretAndApiKey(clientId, clientSecret, apiKey).isPresent();
    }
}
