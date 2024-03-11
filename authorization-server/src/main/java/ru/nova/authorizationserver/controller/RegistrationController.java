package ru.nova.authorizationserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nova.authorizationserver.model.dto.RegistrationDto;
import ru.nova.authorizationserver.services.UserService;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
@SessionAttributes("registrationDto")
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String getPageRegistration(Model model){
        if(!model.containsAttribute("registrationDto")){
            model.addAttribute("registrationDto", new RegistrationDto());
        }
        return "registration";
    }

    @PostMapping
    public String registerNewUser(@Valid RegistrationDto registrationDto, BindingResult bindingResult, RedirectAttributes redirectAttrs){
        if(bindingResult.hasErrors() || !registrationDto.getPassword().equals(registrationDto.getRepeatPassword())){
            redirectAttrs.getFlashAttributes().clear();
            redirectAttrs.addFlashAttribute("registrationDto", registrationDto);
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.registrationDto", bindingResult);
            return "redirect:/registration";
        }
        return "redirect:/registration/confirm";
    }

    @GetMapping("/confirm")
    public String emailConfirm(RegistrationDto registrationDto){
        System.out.println(registrationDto);

        return "emailConfirm";
//        sessionStatus.setComplete();

    }
    @PostMapping("/confirm")
    public String emailCodeConfirm(RegistrationDto registrationDto, SessionStatus sessionStatus){
        System.out.println(registrationDto);
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
