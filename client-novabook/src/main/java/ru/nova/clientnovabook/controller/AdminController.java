package ru.nova.clientnovabook.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @GetMapping
    public String getAdminPage(Model model, Principal principal){
        System.out.println("3333333333333333333");
        return "adminPage";
    }
}
