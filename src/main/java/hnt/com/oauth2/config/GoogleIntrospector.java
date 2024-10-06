package hnt.com.oauth2.config;

import hnt.com.oauth2.dto.UserInfo;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GoogleIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;

    public GoogleIntrospector(WebClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }
    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo user = userInfoClient.get()
                .uri(uriBuilder -> uriBuilder.path("/oath2/v3/userinfo").queryParam("access_token", token).build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", user.name());
        attributes.put("email", user.email());
        return new OAuth2IntrospectionAuthenticatedPrincipal(user.name(), attributes,null);
    }

}
