package ru.nova.clientnovabook.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nova.clientnovabook.model.Mapper;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.*;
import ru.nova.clientnovabook.service.FileUploadService;
import ru.nova.clientnovabook.service.UserService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/client/edit")
@RequiredArgsConstructor
@Log4j2
public class ClientEditController {
    private final UserService userService;
    private final Mapper userMapper;
    private final FileUploadService fileUploadService;
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
        if(!model.containsAttribute("message")){
            model.addAttribute("message", null);
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
        model.addAttribute("listSex", User.Sex.values());
        model.addAttribute("userSex", EditUserSexDto.builder()
                        .sex(user.getSex())
                .build());
        model.addAttribute("userAvatar", EditUserAvatarDto.builder()
                        .avatarName(userMapper.getAvatarName(user))
                .build());
        return "editClientPage";
    }

    @PutMapping("/name")
    public String editName(EditUserNameDto userNameDto, Principal principal, RedirectAttributes redirectAttributes){
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
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Имя успешно изменено на " + userMapper.getFullName(user));
        return "redirect:/client/edit";
    }
    @PutMapping("/phone")
    public String editPhone(EditUserPhoneDto userPhoneDto, Principal principal, RedirectAttributes redirectAttributes){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setPhone(userPhoneDto.getPhone());
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Телефон успешно изменен на " +  user.getPhone());
        return "redirect:/client/edit";
    }

    @PutMapping("/birthday")
    public String editBirthday(EditUserBirthdayDto userBirthdayDto, Principal principal, RedirectAttributes redirectAttributes){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setBirthday(userBirthdayDto.getBirthday());
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "День рождения успешно изменен на " + user.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return "redirect:/client/edit";
    }

    @PutMapping("/sex")
    public String editSex(EditUserSexDto userSexDto, Principal principal, RedirectAttributes redirectAttributes){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        user.setSex(userSexDto.getSex());
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Пол профиля успешно изменен на " + user.getSex().name());
        return "redirect:/client/edit";
    }
    @PutMapping("/avatar")
    public String editAvatar(Principal principal, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        User user;
        try{
            user = userService.findUserByEmail(principal.getName());
            fileUploadService.saveAvatar(file, user);
            userService.save(user);
            redirectAttributes.addFlashAttribute("message", "Аватар профиля успешно изменен");
        }catch (WebClientException e){
            e.printStackTrace();
            throw new RuntimeException(); //TODO
        }
        return "redirect:/client/edit";
    }
}
