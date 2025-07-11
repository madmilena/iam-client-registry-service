package com.hablou.AS1.iam_client_registry_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "app_name", nullable = false)
    private String appName;
}
