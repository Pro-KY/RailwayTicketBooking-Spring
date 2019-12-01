package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.service.UserService;
import com.proky.booking.util.AlertHandler;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.Message;
import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RequestMapping("/admin")
@Controller
@AllArgsConstructor
public class AdminController {
    private ViewPath viewPath;
    private UserService userService;
    private AlertHandler alertHandler;
    private Message message;

    @RequestMapping("/users")
    public String getRegisteredUsers(@RequestParam(required = false) Integer pageIndex,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Boolean prevPageClick,
                                     @RequestParam(required = false) Boolean nextPageClick,
                                     Model model) {

        final PageDto pageDto = new PageDto(pageIndex, pageSize, nextPageClick, prevPageClick);
        log.info("pageDto in getRegisteredUsers: {}", pageDto);

        final PageDto usersPerPage = userService.findAllRegisteredUsers(pageDto);
        model.addAttribute(Attributes.PAGE_DTO, usersPerPage);

        return viewPath.allUsers;
    }

    @GetMapping("/manageUser")
    public String manageUser(@RequestParam Long userId,  Model model) {
        final UserDto userDto = userService.getUserDtoById(userId);
        log.info(userDto);
        model.addAttribute(Attributes.USER, userDto);
        return "/" + viewPath.manageUser;
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        log.info(userDto);
        userService.updateUser(userDto);
        alertHandler.setAlertData(true, message.userUpdated, redirectAttributes);

        return "redirect:/" + viewPath.allUsers;
    }

    @GetMapping("/deleteUser/{id}") // String
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        alertHandler.setAlertData(true, message.userDeleted, redirectAttributes);
        userService.deleteUser(id);
        return "redirect:/" + viewPath.allUsers;
    }
}
