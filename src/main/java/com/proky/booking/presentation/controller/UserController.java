package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotBlank;

@Log4j2
@RequestMapping("/user")
@Controller
@AllArgsConstructor
public class UserController {
    ViewProperties viewProperties;


    @PostMapping("/signIn")
    public RedirectView singIn(@RequestParam @NotBlank String email, @RequestParam @NotBlank String password) {

        return new RedirectView(viewProperties.getIndexPage());
    }

    @PostMapping("/signUp")
    public String singUp() {
        return viewProperties.getSignInPage();
    }

    @PostMapping("/signOut")
    public String singOut() {
        return viewProperties.getSignInPage();
    }
}
