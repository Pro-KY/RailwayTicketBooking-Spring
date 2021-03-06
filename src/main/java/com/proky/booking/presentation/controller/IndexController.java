package com.proky.booking.presentation.controller;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.service.StationService;
import com.proky.booking.util.MessageSourceWrapper;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return viewPath.index;
    }

}
