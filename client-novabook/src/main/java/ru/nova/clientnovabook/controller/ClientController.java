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
import ru.nova.clientnovabook.model.dto.AddCommentDto;
import ru.nova.clientnovabook.model.dto.WallDto;
import ru.nova.clientnovabook.service.PostService;
import ru.nova.clientnovabook.model.Mapper;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.PostDto;
import ru.nova.clientnovabook.service.UserService;
import ru.nova.clientnovabook.service.WallService;

import java.security.Principal;

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
    public String getClientPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                                @RequestParam(required = false, defaultValue = "DESC") String direction,
                                @RequestParam(required = false, defaultValue = "dateCreation") String sortByField,
                                Model model, Principal principal
    ){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            user = userService.createNewUser(); //TODO
        }
        model.addAttribute("wall", wallService.getWallByOwnerId(user.getUserId(), pageNumber, pageSize, direction, sortByField));
        model.addAttribute("postDto", PostDto.builder().build());
        model.addAttribute("user", mapper.toDto(user));
        model.addAttribute("commentDto", new AddCommentDto());
        model.addAttribute("visitStatus", "owner");
        return "clientPage";
    }

    @GetMapping("/{id}")
    public String getClientIdPage(@PathVariable("id") long id,
                                  @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                                  @RequestParam(required = false, defaultValue = "DESC") String direction,
                                  @RequestParam(required = false, defaultValue = "dateCreation") String sortByField,
                                  Model model
    ){
        model.addAttribute("user", mapper.toDto(userService.findUserById(id)));
        model.addAttribute("wall", wallService.getWallByOwnerId(id, pageNumber, pageSize, direction, sortByField));
        model.addAttribute("postDto", PostDto.builder().build());
        model.addAttribute("visitStatus", "guest");
        return "clientPage";
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
