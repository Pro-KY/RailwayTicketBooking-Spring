package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.SqlDateTimeConverter;
import com.proky.booking.util.constans.http.Attributes;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RequestMapping("/trains")
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.PAGE_DTO)
public class TrainController {
    TrainService trainService;

    @PostMapping("/find")
    public String findTrain(@RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            @SessionAttribute(required = false) PageDto pageDto,
                            Model model) {

        pageDto = (pageDto == null) ? new PageDto() : pageDto;
        log.info("pageDto {}", pageDto);

        final PageDto trains = trainService.findTrains(pageDto, departureDate, departureTime, goingTo);

        log.info("POST findTrain is called");
        log.info(goingTo);
        log.info(departureDate);
        log.info(departureTime);

        return "index";
    }

    @GetMapping("/find")
    public RedirectView findTrain(@RequestParam(name="pageSize") String pageSize,
                            @RequestParam(name="prevPageClick", required = false) Boolean prevPageClick,
                            @RequestParam(name="nextPageClick", required = false) Boolean nextPageClick,
                            @RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            Model model) {



        log.info("GET findTrain called");

        log.info("pageSize {}", pageSize);
        log.info("prevPageClick {}", prevPageClick);
        log.info("nextPageClick {}", nextPageClick);

        return new RedirectView("/index");
//        return "redirect:/index";
    }
}
