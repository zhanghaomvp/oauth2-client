package com.example.cetcxl.client.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
@EnableOAuth2Client
public class Oauth2ClientConfig {
    @Value("${auth-server}")
    private String ssoServerUrl;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.clientRegistration());
    }

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("cetcxl")
                .clientId("cetcxl-client3")
                .clientSecret("123")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("all")
                .authorizationUri(ssoServerUrl + "/oauth/authorize")
                .tokenUri(ssoServerUrl + "/oauth/token")
                .userInfoUri(ssoServerUrl + "/user")
                .userNameAttributeName("name")
                .build();
    }
}
