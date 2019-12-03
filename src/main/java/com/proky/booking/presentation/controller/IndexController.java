package com.proky.booking.presentation.controller;

import com.proky.booking.dto.UserDto;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.service.SignInService;
import com.proky.booking.service.SignUpService;
import com.proky.booking.service.StationService;
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
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Controller
@AllArgsConstructor
public class IndexController {
    private StationService stationService;
    private ViewPath viewPath;

    @RequestMapping(path = {"/", "/trains"})
    public String indexPage(Model model) {
        final List<Station> allStations = stationService.findAllStations();
        model.addAttribute(Attributes.STATIONS, allStations);
        log.info("indexPage called");
        return viewPath.index;
    }

}
