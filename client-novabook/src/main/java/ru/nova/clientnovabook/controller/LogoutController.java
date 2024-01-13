package ru.nova.clientnovabook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logged-out")
public class LogoutController {

    @GetMapping()
    public String loggedOut(Model model, Authentication authentication) {
        model.addAttribute("login", authentication);
        return "index";
    }
}
