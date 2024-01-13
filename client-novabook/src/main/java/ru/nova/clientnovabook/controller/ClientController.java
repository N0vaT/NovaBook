package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nova.clientnovabook.config.WelcomeClient;
import ru.nova.clientnovabook.model.User;

import java.security.Principal;

@Controller
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

//    private final UserService userService;
    private final WelcomeClient welcomeClient;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping()
//    @GetMapping("/{smth}")
//    @PreAuthorize("#something == authentication.name")
//    public String getClientPage(@PathVariable("smth") String something, Model model, Principal principal){
    public String getClientPage(Model model, Principal principal, Authentication authentication){
        System.out.println(authentication.getAuthorities().toString());

//        User user = userService.findUserByName("LOL");
//        System.out.println(user.toString());
        User user = welcomeClient.getWelcome();
        System.out.println(user);
        if(principal!=null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "clientPage";
    }

    @GetMapping("/token")
    @ResponseBody
    public String token(Authentication authentication) {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService
                .loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), oAuth2AuthenticationToken.getName());
        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        String jwtRefrechToken = oAuth2AuthorizedClient.getRefreshToken().getTokenValue();
        return "<b>JWT Access Token: </b>" + jwtAccessToken + "<br/><br/><b>JWT Refresh Token:  </b>" + jwtRefrechToken;
    }

    @GetMapping("/idtoken")
    @ResponseBody
    public String idtoken(@AuthenticationPrincipal OidcUser oidcUser) {
        OidcIdToken oidcIdToken = oidcUser.getIdToken();
        String idTokenValue = oidcIdToken.getTokenValue();
        return "<b>Id Token: </b>" + idTokenValue;
    }
}
