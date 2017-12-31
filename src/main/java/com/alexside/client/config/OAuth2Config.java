package com.alexside.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import static java.util.Arrays.asList;

/**
 * Created by Alex on 31.12.2017.
 */
@Configuration
@EnableOAuth2Client
public class OAuth2Config {

    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${config.oauth2.clientID}")
    private String clientID;

    @Value("${config.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${config.oauth2.resourceID}")
    private String resourceID;

    @Value("${config.oauth2.username}")
    private String username;

    @Value("${config.oauth2.password}")
    private String password;

    @Bean
    public OAuth2RestTemplate restTemplate() {
        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
    }

    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setId(resourceID);
        resource.setClientId(clientID);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(accessTokenUri);
        //resource.setUserAuthorizationUri(userAuthorizationUri);
        resource.setScope(asList("read", "write"));
        resource.setUsername(username);
        resource.setPassword(password);
        return resource;
    }
}
