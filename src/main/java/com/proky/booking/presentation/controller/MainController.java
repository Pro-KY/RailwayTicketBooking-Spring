package com.proky.booking.presentation.controller;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.service.StationService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.STATIONS)
public class MainController {
    private StationService stationService;
    private ViewProperties viewProperties;

    @RequestMapping(path = {"/", "index"})
    public String indexPage(Model model) {
        final List<Station> allStations = stationService.findAllStations();
        model.addAttribute(Attributes.STATIONS, allStations);
        log.info("index called");
        return viewProperties.getIndexPage();
    }

    @GetMapping("/signInPage")
    public String signInPage() {
        return viewProperties.getSignInPage();
    }

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return viewProperties.getSignUpPage();
    }
}
