package ru.nova.clientnovabook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nova.clientnovabook.model.Mapper;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserService userService;
    private final Mapper mapper;
    @GetMapping
    public String getAdminPage(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users.stream().map(mapper::toDto).toList());
        System.out.printf(userService.findUsers().toString());
        return "searchPage";
    }
}