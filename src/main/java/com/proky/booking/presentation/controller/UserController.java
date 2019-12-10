package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.security.SecurityService;
import com.proky.booking.util.AlertHandler;
import com.proky.booking.util.MessageSourceWrapper;
import com.proky.booking.util.constans.RoleEnum;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@AllArgsConstructor
public class UserController {
    private ViewPath viewPath;
    private SignUpService signUpService;
    private AlertHandler alertHandler;
    private SecurityService securityService;
    private MessageSourceWrapper messageSourceWrapper;

    @GetMapping("/login")
    public String login(String error) {
        return (securityService.isNotAnonymousUser()) ? "redirect:/defaultAfterLogin" : viewPath.login;
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute(Attributes.USER_DTO, new UserDto());
        return viewPath.signUp;
    }

    @RequestMapping("/defaultAfterLogin")
    public String defaultAfterLogin() {
        final String userRole = securityService.getUserRole();

        String viewhUrl = userRole.equals(RoleEnum.ADMIN.role) ? viewPath.allUsers : "trains";
        return "redirect:/" + viewhUrl;
    }

    @PostMapping("/signUp")
    public String singUp(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info(userDto);

        if (bindingResult.hasErrors()) {
            return viewPath.signUp;
        } else {
            signUpService.signUp(userDto);
            alertHandler.setAlertData(true, messageSourceWrapper.getMessage("user.created.msg"), redirectAttributes);
        }

        return "redirect:/" + viewPath.login;
    }
}
