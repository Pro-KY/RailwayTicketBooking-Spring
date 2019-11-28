package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.UserService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.constraints.NotBlank;

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

    @PostMapping("/signIn")
    public String singIn(@RequestParam @NotBlank String email, @RequestParam @NotBlank String password, Model model) {
        final UserDto userCredentials = new UserDto(email, password);
        final User authenticatedUser = signInService.signIn(userCredentials);
        log.info("user is signed in");

        final boolean isAdministrator = userService.isAdministrator(authenticatedUser);
        final UserDto userDto = modelMapper.map(authenticatedUser, UserDto.class);
        log.info(userDto);
        userDto.setAdministrator(isAdministrator);

        String viewPathUrl = isAdministrator ? view.adminUsers : view.index;
        model.addAttribute(Attributes.USER, userDto);

        return "redirect:/" + viewPathUrl;
    }

    @PostMapping("/signUp")
    public String singUp() {
        return view.signIn;
    }

    @PostMapping("/signOut")
    public String singOut() {
        return view.signIn;
    }
}
