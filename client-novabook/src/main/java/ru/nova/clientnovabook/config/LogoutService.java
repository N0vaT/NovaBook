package ru.nova.clientnovabook.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
//public class LogoutService implements LogoutHandler {
public class LogoutService {

//    private final OAuth2AuthorizedClientService clientService;
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        RestTemplate restTemplate = new RestTemplate();
//        String accessToken = null;
//        if (authentication.getClass()
//                .isAssignableFrom(OAuth2AuthenticationToken.class)) {
//            OAuth2AuthenticationToken oauthToken =
//                    (OAuth2AuthenticationToken) authentication;
//            String clientRegistrationId =
//                    oauthToken.getAuthorizedClientRegistrationId();
//            if (clientRegistrationId.equals("nb-client-oidc")) {
//                OAuth2AuthorizedClient client =
//                        clientService.loadAuthorizedClient(
//                                clientRegistrationId, oauthToken.getName());
//                accessToken = client.getAccessToken().getTokenValue();
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//                HttpEntity<?> entity = new HttpEntity(headers);
//                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:9000/oauth2/revoke")
//                        .queryParam("access_token", accessToken)
//                        .queryParam("Authorization", "Basic " + "username=nb-client password=secret");
//                System.out.println(entity.toString());
//                System.out.println(accessToken);
//                restTemplate.postForObject(builder.build().encode().toUri(), entity, Void.class);
//                clientService.removeAuthorizedClient("nb-client-oidc", authentication.getName());
//            }
//        }
//    }
}
