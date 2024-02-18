package ru.nova.clientnovabook.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getIndex(){
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("getIndex isAuthenticated() - " + SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        return "index";
    }
}
