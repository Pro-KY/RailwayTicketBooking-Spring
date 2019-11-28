package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.UserService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/admin")
@Controller
@AllArgsConstructor
public class AdminController {
    private ViewProperties viewProperties;
    private UserService userService;

    @RequestMapping("/users")
    public String getRegisteredUsers(Model model) {
        log.info("getRegisteredUsers called");
        PageDto pageDto = new PageDto();
        final PageDto usersPerPage = userService.findAllRegisteredUsers(pageDto);
        model.addAttribute(Attributes.PAGE_DTO, usersPerPage);

        return viewProperties.adminUsersView;
    }

    @GetMapping("/manageUser")
    public String manageUser(Model model) {
        return null;
    }

}
