package com.novatax.client.portal.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class KeycloakConfiguration {
    private static final String SERVER_URL = "http://localhost:8180/";
    private static final String REALM = "novaclientportal";
    private static final String CLIENT_ID = "novaclientapi";
    private static final String CLIENT_SECRET = "c0V3JnP2SsNjR8dViepebW7uei9vaBdG";

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .build();
    }
}
