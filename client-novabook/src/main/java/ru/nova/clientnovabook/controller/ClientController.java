package ru.nova.clientnovabook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/client")
public class ClientController {

    @GetMapping
    public String getClientPage(Model model, Principal principal){
        if(principal!=null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "clientPage";
    }
}
