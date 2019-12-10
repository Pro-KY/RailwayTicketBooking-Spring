package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.service.UserService;
import com.proky.booking.util.AlertHandler;
import com.proky.booking.util.MessageSourceWrapper;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
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
    private MessageSourceWrapper messageSourceWrapper;

    @RequestMapping("/users")
    public String getRegisteredUsers(@RequestParam(required = false) Integer pageIndex,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Boolean prevPageClick,
                                     @RequestParam(required = false) Boolean nextPageClick,
                                     Model model) {

        final PageDto pageDto = new PageDto(pageIndex, pageSize, nextPageClick, prevPageClick);
        final PageDto usersPerPage = userService.findAllRegisteredUsers(pageDto);
        model.addAttribute(Attributes.PAGE_DTO, usersPerPage);

        return viewPath.allUsers;
    }

    @GetMapping("/manageUser")
    public String manageUser(@RequestParam Long userId,  Model model) {
        final UserDto userDto = userService.getUserDtoById(userId);
        model.addAttribute(Attributes.USER_DTO, userDto);
        return "/" + viewPath.manageUser;
    }

    @PutMapping("/updateUser")
    public String updateUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        userService.updateUser(userDto);
        alertHandler.setAlertData(true, messageSourceWrapper.getMessage("user.updated.msg"), redirectAttributes);

        return "redirect:/" + viewPath.allUsers;
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        alertHandler.setAlertData(true, messageSourceWrapper.getMessage("user.deleted.msg"), redirectAttributes);
        userService.deleteUser(id);
        return "redirect:/" + viewPath.allUsers;
    }
}
