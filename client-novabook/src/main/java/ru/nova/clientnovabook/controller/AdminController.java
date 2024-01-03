package ru.nova.clientnovabook.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("#authentication.authorities.contains('ROLE_ADMIN')")
    public String getAdminPage(Model model, Principal principal, Authentication authentication){
        System.out.println(authentication.getDetails().toString());
        System.out.println(authentication.getAuthorities().toString());
        System.out.println(principal.getName());
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        System.out.println("getAdminPage isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println("getAdminPage isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println("3333333333333333333");
        return "adminPage";
    }
}
