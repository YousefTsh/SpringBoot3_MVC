package com.spring.mvc.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class SecurityUtil {
    private final OAuth2AuthorizedClientService authorizedClientService;
    public String getSessionUser() {

        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUsername = authentication.getName();
                return currentUsername;
            } else if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                OAuth2AuthorizedClient client = authorizedClientService
                        .loadAuthorizedClient(
                                authentication.getAuthorizedClientRegistrationId(),
                                authentication.getName());
                String userInfoEndpointUri = client.getClientRegistration()
                        .getProviderDetails().getUserInfoEndpoint().getUri();

                if (!userInfoEndpointUri.isEmpty()) {
                    RestTemplate restTemplate = new RestTemplate(); // used to send http request
                    HttpHeaders headers = new HttpHeaders(); // to add http header
                    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
                            .getTokenValue());
                    HttpEntity entity = new HttpEntity("", headers); // to prepeare http request by setheader and request body
                    ResponseEntity<Map> response = restTemplate
                            .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class); // use rest templetel instance tosend request by set url and method and httpentity
                    Map userAttributes = response.getBody();
                    String username="";
                    if (userAttributes.containsKey("login")){
                        username = userAttributes.get("login").toString();
                    }else if (userAttributes.containsKey("name")){
                        username = userAttributes.get("name").toString();
                    }
                    String currentUsername = username;
                    return currentUsername;
                }

            }
        }
        return null;
    }
}
