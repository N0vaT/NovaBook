package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.EditUserBirthdayDto;
import ru.nova.clientnovabook.model.dto.EditUserNameDto;
import ru.nova.clientnovabook.model.dto.EditUserPhoneDto;
import ru.nova.clientnovabook.model.dto.EditUserSexDto;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/client/edit")
@AllArgsConstructor
@Log4j2
public class ClientEditController {
    private final UserService userService;
    @GetMapping()
    public String getEditClientPage(Model model, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
//            log.info("Create new client, email - {}", principal.getName());
//            user = userService.createNewUser();
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        model.addAttribute("userName",
                EditUserNameDto.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .patronymic(user.getPatronymic())
                .build());
        model.addAttribute("userPhone", EditUserPhoneDto.builder()
                        .phone(user.getPhone())
                .build());
        model.addAttribute("userBirthday", EditUserBirthdayDto.builder()
                .birthday(user.getBirthday())
                .build());
        model.addAttribute("userSex", EditUserSexDto.builder()
                        .sex(user.getSex())
                .build());
        return "editClientPage";
    }

    @PutMapping("/name")
    public String editName(EditUserNameDto userNameDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setFirstName(userNameDto.getFirstName());
        user.setLastName(userNameDto.getLastName());
        user.setPatronymic(userNameDto.getPatronymic());
        log.info("User name changed - {}", userNameDto);
        userService.save(user);
        return "redirect:/client/edit";
    }
    @PutMapping("/phone")
    public String editPhone(EditUserPhoneDto userPhoneDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setPhone(userPhoneDto.getPhone());
        log.info("User phone changed - {}", userPhoneDto);
        userService.save(user);
        return "redirect:/client/edit";
    }

    @PutMapping("/birthday")
    public String editBirthday(EditUserBirthdayDto userBirthdayDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setBirthday(userBirthdayDto.getBirthday());
        log.info("User birthday changed - {}", userBirthdayDto);
        userService.save(user);
        return "redirect:/client/edit";
    }

    @PutMapping("/sex")
    public String editSex(EditUserSexDto userSexDto, Principal principal){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setSex(userSexDto.getSex());
        log.info("User sex changed - {}", userSexDto);
        userService.save(user);
        return "redirect:/client/edit";
    }
}
