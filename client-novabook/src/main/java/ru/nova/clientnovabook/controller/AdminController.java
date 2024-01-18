package ru.nova.clientnovabook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdminPage(Model model, Principal principal, Authentication authentication){
        List<User> users = userService.findUsers();
        System.out.printf(userService.findUsers().toString());
        return "adminPage";
    }
}
