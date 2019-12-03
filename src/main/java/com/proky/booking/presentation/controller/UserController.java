package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.UserService;
import com.proky.booking.service.security.SecurityService;
import com.proky.booking.util.AlertHandler;
import com.proky.booking.util.constans.enums.UserRoleEnum;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.Message;
import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.USER)
public class UserController {
    private ViewPath viewPath;
    private SignUpService signUpService;
    private AlertHandler alertHandler;
    private Message message;
    private SecurityService securityService;

    @GetMapping("/login")
    public String signIn(Model model, String error) {
        log.info("here in singIn page");
        log.info("error {}", error);
        return (!securityService.isAnonymousUser()) ? "redirect:/defaultAfterLogin" : viewPath.login;
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute(Attributes.USER, new UserDto());
        return viewPath.signUp;
    }

    @RequestMapping("/defaultAfterLogin")
    public String defaultAfterLogin(HttpServletRequest request) {
        log.info("defaultAfterLogin called");

        final String userRole = securityService.getUserRole();
        log.info(userRole);

        String viewhUrl = userRole.equals(UserRoleEnum.ADMIN.role) ? viewPath.allUsers : "trains";
        return "redirect:/" + viewhUrl;
    }

    @PostMapping("/signUp")
    public String singUp(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info(userDto);
        final User signedUpUser = signUpService.signUp(userDto);
        alertHandler.setAlertData(true, message.userCreated, redirectAttributes);

        return "redirect:/" + viewPath.login;
    }
}
