package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RequestMapping("/trains")
@Controller
@AllArgsConstructor
@SessionAttributes(Attributes.MODEL)
//@SessionAttributes({Attributes.PAGE_DTO, Attributes.MODEL})
public class TrainController {
    private View view;
    private TrainService trainService;

    @PostMapping("/find")
    public String findTrain(@RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                                  RedirectAttributes attributes, Model model) {


        log.info("find train POST");
        final PageDto pageDto = new PageDto();
        final PageDto trains = trainService.findTrains(pageDto, departureDate, departureTime, goingTo);
        model.addAttribute(Attributes.MODEL, trains);

        attributes.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        attributes.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        attributes.addAttribute(Attributes.GOING_TO, goingTo);

        log.info("POST findTrain is called");

        return "redirect:/trains";
//        return new RedirectView(viewProperties.indexView);
    }

    @GetMapping("/find")
    public String findTrain(@RequestParam(name="pageSize") Integer pageSize,
                            @RequestParam(name="prevPageClick", required = false) Boolean prevPageClick,
                            @RequestParam(name="nextPageClick", required = false) Boolean nextPageClick,
                            @RequestParam(name="pageIndex", required = false) Integer selectedPageIndex,
                            @RequestParam(name="goingTo") String goingTo,
                            @RequestParam(name="departureDate") String departureDate,
                            @RequestParam(name="departureTime") String departureTime,
                            Model model) {

        log.info("find train GET");
        final PageDto pageDto = new PageDto(selectedPageIndex, pageSize, prevPageClick, nextPageClick);
        final PageDto trains = trainService.findTrains(pageDto, departureDate, departureTime, goingTo);
        log.info(pageDto);

        model.addAttribute(Attributes.MODEL, trains);
        model.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        model.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        model.addAttribute(Attributes.GOING_TO, goingTo);

        return view.index;
    }
}
