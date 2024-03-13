package ru.nova.authorizationserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nova.authorizationserver.config.utils.EmailCodeGenerator;
import ru.nova.authorizationserver.model.StringValue;
import ru.nova.authorizationserver.model.dto.RegistrationDto;
import ru.nova.authorizationserver.services.UserService;
import ru.nova.authorizationserver.services.kafka.MailConfirmSender;

import java.time.LocalDateTime;

@Log4j2
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
@SessionAttributes("registrationDto")
public class RegistrationController {
    private final UserService userService;
    private final MailConfirmSender mailConfirmSender;
    private String emailCode;
    private LocalDateTime dateCode;

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
        emailCode = EmailCodeGenerator.generateCode();
        dateCode = LocalDateTime.now();
        mailConfirmSender.send(new StringValue(registrationDto.getEmail(), emailCode, dateCode));
        return "emailConfirm";

    }
    @PostMapping("/confirm")
    public String emailCodeConfirm(RegistrationDto registrationDto, SessionStatus sessionStatus, String code){
        LocalDateTime endDateCode = dateCode.plusMinutes(3L);
        if(dateCode.isAfter(endDateCode) || !emailCode.equals(code)){
            log.error("Code confirmation failure ({}-{})", emailCode, code);
            return "redirect:/registration/confirm";
        }
        userService.saveUser(registrationDto);
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
