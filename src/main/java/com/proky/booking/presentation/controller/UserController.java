package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.UserService;
import com.proky.booking.util.AlertHandler;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.Message;
import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

@Log4j2
@RequestMapping("/user")
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.USER)
public class UserController {
    private ViewPath viewPath;
    private SignInService signInService;
    private SignUpService signUpService;
    private UserService userService;
    private ModelMapper modelMapper;
    private AlertHandler alertHandler;
    private Message message;

    @GetMapping("/signInPage")
    public String signInPage() {
        return viewPath.signIn;
    }

    @GetMapping("/signUpPage")
    public String signUpPage(Model model) {
        model.addAttribute(Attributes.USER, new UserDto());
        return viewPath.signUp;
    }

    @PostMapping("/signIn")
    public String singIn(@RequestParam @NotBlank String email, @RequestParam @NotBlank String password, Model model) {
        final UserDto userCredentials = new UserDto(email, password);
        final User authenticatedUser = signInService.signIn(userCredentials);
        log.info("user is signed in");

        final boolean isAdministrator = userService.isAdministrator(authenticatedUser);
        final UserDto userDto = modelMapper.map(authenticatedUser, UserDto.class);
        log.info(userDto);
        userDto.setAdministrator(isAdministrator);

        String viewhUrl = isAdministrator ? viewPath.allUsers : "trains";
        model.addAttribute(Attributes.USER, userDto);

        return "redirect:/" + viewhUrl;
    }

    @PostMapping("/signUp")
    public String singUp(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info(userDto);
        signUpService.signUp(userDto);
        alertHandler.setAlertData(true, message.userCreated, redirectAttributes);
        return "redirect:/" + viewPath.signIn;
    }

    @GetMapping("/signOut")
    public String singOut(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.invalidate();
        log.info("user is signed out");
        return "redirect:/";
    }
}
