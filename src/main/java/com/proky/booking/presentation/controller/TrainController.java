package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.SqlDateTimeConverter;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RequestMapping("/trains")
@Controller
@AllArgsConstructor
@SessionAttributes({Attributes.PAGE_DTO, Attributes.MODEL})
public class TrainController {
    ViewProperties viewProperties;
    TrainService trainService;

    @PostMapping("/find")
    public RedirectView findTrain(@RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            @SessionAttribute(required = false) PageDto pageDto,
                                                    RedirectAttributes attributes, Model model) {

        pageDto = (pageDto == null) ? new PageDto() : pageDto;
        log.info("pageDto {}", pageDto);

        final PageDto trains = trainService.findTrains(pageDto, departureDate, departureTime, goingTo);
        log.info("trains no null, {}", trains != null);
        model.addAttribute(Attributes.MODEL, trains);

        attributes.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        attributes.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        attributes.addAttribute(Attributes.GOING_TO, goingTo);

        log.info("POST findTrain is called");

        return new RedirectView("/index");
    }

    @GetMapping("/find")
    public String findTrain(@RequestParam(name="pageSize") Integer pageSize,
                            @RequestParam(name="prevPageClick", required = false) Boolean prevPageClick,
                            @RequestParam(name="nextPageClick", required = false) Boolean nextPageClick,
                            @RequestParam(name="pageIndex", required = false) Integer selectedPageIndex,
                            @RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            @SessionAttribute(name = Attributes.MODEL, required = false) PageDto pageDto,
                            Model model) {

        log.info("==== GET findTrain called ==== ");

        log.info("dto session: {}", pageDto.toString());

        log.info("pageSize {}", pageSize);
        log.info("goingTo {}", goingTo);
        log.info("selectedPageIndex {}", selectedPageIndex);
        log.info("departureDate {}", departureDate);
        log.info("departureTime {}", departureTime);
        log.info("prevPageClick {}", prevPageClick);
        log.info("nextPageClick {}", nextPageClick);

        pageDto.updatePageDtoValues(pageSize, prevPageClick, nextPageClick, selectedPageIndex);
        final PageDto trains = trainService.findTrains(pageDto, departureDate, departureTime, goingTo);

        model.addAttribute(Attributes.MODEL, trains);
        model.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        model.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        model.addAttribute(Attributes.GOING_TO, goingTo);

        return viewProperties.getINDEX();
    }
}
