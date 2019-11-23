package com.proky.booking.presentation.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequestMapping("/trains")
@Controller
public class TrainController {

    @PostMapping("/find")
    public String findTrain(Model model) {
        log.info("POST findTrain is called");
        return "index";
    }

    @GetMapping("/find")
    public String findTrainGet(@RequestParam(name="pageSize") String pageSize,
                               @RequestParam(name="goingTo") String goingTo,
                               @RequestParam(name="departureDate") String departureDate,
                               @RequestParam(name="departureTime") String departureTime,
                               Model model) {
        log.info("GET findTrain called");
        return "index";
    }
}
