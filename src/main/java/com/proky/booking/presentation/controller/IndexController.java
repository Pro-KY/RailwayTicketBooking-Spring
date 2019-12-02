package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.StationService;
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
import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {
    private StationService stationService;
    private ViewPath viewPath;
    private SignInService signInService;
    private SignUpService signUpService;
    private UserService userService;
    private ModelMapper modelMapper;
    private AlertHandler alertHandler;
    private Message message;
    private SecurityService securityService;

    @RequestMapping(path = {"/", "/trains"})
    public String indexPage(Model model) {
        final List<Station> allStations = stationService.findAllStations();
        model.addAttribute(Attributes.STATIONS, allStations);
        log.info("indexPage called");
        return viewPath.index;
    }

    @GetMapping("/login")
    public String signIn(Model model, String error) {
        log.info("here in singIn page");
        log.info("error {}", error);
        return viewPath.login;
    }

    @GetMapping("/signUp")
//    public String signUpPage(Model model) {
    public String signUp(Model model) {
        model.addAttribute(Attributes.USER, new UserDto());
        return viewPath.signUp;
    }


    @RequestMapping("/defaultAfterLogin")
    public String defaultAfterLogin(HttpServletRequest request) {
        String viewhUrl = request.isUserInRole("ROLE_ADMIN") ? viewPath.allUsers : "trains";
        return "redirect:/" + viewhUrl;
    }

    @PostMapping("/signUp")
    public String singUp(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info(userDto);
        signUpService.signUp(userDto);
        alertHandler.setAlertData(true, message.userCreated, redirectAttributes);
        securityService.autoLogin(userDto.getEmail(), userDto.getPassword());

        return "redirect:/" + viewPath.login;
    }
}
