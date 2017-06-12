package com.babcock.securityweb.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
@EnableDiscoveryClient
@EnableCircuitBreaker
//@EnableResourceServer
@Profile({"dev","qa","prod"})
@ComponentScan("com.babcock.securityweb")
@Import({SwaggerConfiguration.class})
public class CloudConfiguration {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.grant-type}")
    private String grantType;

    @Bean
    public OAuth2RestTemplate restTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails ();
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setGrantType(grantType);

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        return new OAuth2RestTemplate(resourceDetails,clientContext);
    }
}
