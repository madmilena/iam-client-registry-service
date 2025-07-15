package com.hablou.AS1.iam_client_registry_service.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientIdAndClientSecretAndApiKey(String clientId, String clientSecret, String apiKey);
}
