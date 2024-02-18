package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import ru.nova.clientnovabook.service.WallService;
import ru.nova.clientnovabook.webClient.WallWebClient;
import ru.nova.clientnovabook.model.Mapper;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/client")
@AllArgsConstructor
@Log4j2
public class ClientController {

    private final UserService userService;
    private final WallService wallService;
    private final Mapper mapper;
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping()
//    @GetMapping("/{smth}")
//    @PreAuthorize("#authentication.authenticated == true")
//    public String getClientPage(@PathVariable("smth") String something, Model model, Principal principal){
    public String getClientPage(Model model, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        model.addAttribute("posts", wallService.findPostsByOwnerId(user.getUserId()));
        model.addAttribute("postDto", PostDto.builder().build());
        model.addAttribute("user", mapper.toDto(user));
        return "clientPage";
    }

    @GetMapping("/{id}")
    public String getClientIdPage(@PathVariable("id") long id, Model model){
        model.addAttribute("user", mapper.toDto(userService.findUserById(id)));
        model.addAttribute("posts", wallService.findPostsByOwnerId(id));
        model.addAttribute("postDto", PostDto.builder().build());
        return "clientPage";
    }
    @PostMapping("/post")
    public String addPost(PostDto postDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        postDto.setOwnerId(user.getUserId());
        wallService.createPost(postDto);
        return "redirect:/client";
    }





    @GetMapping("/token")
    @ResponseBody
    public String token(Authentication authentication) {
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
