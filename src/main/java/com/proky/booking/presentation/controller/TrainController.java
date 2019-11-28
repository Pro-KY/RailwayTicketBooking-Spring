package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.SqlDateTimeConverter;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.Time;

@Log4j2
@RequestMapping("/trains")
@Controller
@AllArgsConstructor
//@SessionAttributes({Attributes.PAGE_DTO, Attributes.MODEL})
public class TrainController {
    private View view;
    private TrainService trainService;
    SqlDateTimeConverter sqlDateTimeConverter;

    @PostMapping("/find")
    public String findTrain(@RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                                  RedirectAttributes attributes) {

        log.info("POST findTrain is called");
        final PageDto actualPageDto = new PageDto();

        final PageDto updatedPageDto = trainService.findTrains(actualPageDto, departureDate, departureTime, goingTo);
        log.info(updatedPageDto);

        attributes.addFlashAttribute(Attributes.PAGE_DTO, updatedPageDto);
        attributes.addFlashAttribute(Attributes.DEPARTURE_DATE, departureDate);
        attributes.addFlashAttribute(Attributes.DEPARTURE_TIME, departureTime);
        attributes.addFlashAttribute(Attributes.GOING_TO, goingTo);

        return "redirect:/trains";
    }

    @GetMapping("/find")
    public String findTrain(@RequestParam(name="pageSize") Integer pageSize,
                            @RequestParam(required = false) Boolean prevPageClick,
                            @RequestParam(required = false) Boolean nextPageClick,
                            @RequestParam(required = false) Integer pageIndex,
                            @RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            Model model) {

        log.info("find train GET");
        final PageDto actualPageDto = new PageDto(pageIndex, pageSize, nextPageClick, prevPageClick);
        final PageDto updatedPageDto = trainService.findTrains(actualPageDto, departureDate, departureTime, goingTo);
        log.info(actualPageDto);

        model.addAttribute(Attributes.PAGE_DTO, updatedPageDto);
        model.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        model.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        model.addAttribute(Attributes.GOING_TO, goingTo);

        return view.index;
    }
}
