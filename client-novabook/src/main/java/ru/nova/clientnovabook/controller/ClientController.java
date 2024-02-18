package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final UserService userService;

    @GetMapping()
//    @GetMapping("/{smth}")
//    @PreAuthorize("#something == authentication.name")
//    public String getClientPage(@PathVariable("smth") String something, Model model, Principal principal){
    public String getClientPage(Model model, Principal principal){
        User user = userService.findUserByName("LOL");
        System.out.println(user.toString());
        if(principal!=null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "clientPage";
    }
}
