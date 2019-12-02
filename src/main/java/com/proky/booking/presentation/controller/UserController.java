package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.UserService;
import com.proky.booking.service.security.SecurityService;
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

import javax.servlet.http.HttpServletRequest;
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
    private SecurityService securityService;

//    @GetMapping("/signInPage")
//    public String signInPage() {
//        log.info("here in singIn page");
//        return viewPath.signIn;
//    }



}
