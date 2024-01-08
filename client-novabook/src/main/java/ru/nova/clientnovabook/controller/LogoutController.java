package ru.nova.clientnovabook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogoutController {

    @GetMapping
    public String logout(Authentication authentication){
        return "redirect:http://127.0.0.1:9000/admin/oauth2/revoke";
    }
}
