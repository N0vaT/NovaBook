package ru.nova.clientnovabook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nova.clientnovabook.model.mapper.UserMapper;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdminPage(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users.stream().map(userMapper::toDto).toList());
        return "adminPage";
    }
}
