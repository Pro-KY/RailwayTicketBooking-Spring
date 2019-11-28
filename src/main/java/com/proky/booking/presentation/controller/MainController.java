package com.proky.booking.presentation.controller;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.service.StationService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
public class MainController {
    private StationService stationService;
    private View view;

//    @RequestMapping(path = {"/booking", "/trains"})
    @RequestMapping(path = {"/", "/trains"})
//    @RequestMapping("/trains")
    public String indexPage(Model model) {
        final List<Station> allStations = stationService.findAllStations();
        model.addAttribute(Attributes.STATIONS, allStations);
        log.info("index called");
        return view.index;
    }

    @GetMapping("/signInPage")
    public String signInPage() {
        return view.signIn;
    }

    @GetMapping("/signUpPage")
    public String signUpPage() {
        return view.signUp;
    }
}
