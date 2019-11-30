package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.UserService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.Optional;

@Log4j2
@RequestMapping("/user")
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.USER)
public class UserController {
    private View view;
    private SignInService signInService;
    private UserService userService;
    private ModelMapper modelMapper;

    @GetMapping("/signInPage")
    public String signInPage() {
        return view.signIn;
    }

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return view.signUp;
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

        String viewhUrl = isAdministrator ? view.adminUsers : "trains";
        model.addAttribute(Attributes.USER, userDto);

        return "redirect:/" + viewhUrl;
    }

    @PostMapping("/signUp")
    public String singUp() {
        return view.signIn;
    }

    @GetMapping("/signOut")
    public String singOut(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.invalidate();
        log.info("user is signed out");
        return "redirect:/";
    }
}
