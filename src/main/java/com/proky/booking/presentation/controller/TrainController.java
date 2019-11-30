package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.dto.TrainDto;
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
//@SessionAttributes({Attributes.PAGE_DTO, Attributes.MODEL})
public class TrainController {
    private View view;
    private TrainService trainService;


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
    public String findTrainsUsingPagination(
        @RequestParam(name="pageSize") Integer pageSize,
        @RequestParam(required = false) Boolean prevPageClick,
        @RequestParam(required = false) Boolean nextPageClick,
        @RequestParam(required = false) Integer pageIndex,
        @RequestParam(name="goingTo") String goingTo,
        @RequestParam(name="departureDate") String departureDate,
        @RequestParam(name="departureTime") String departureTime,
        Model model) {

        log.info("find trains GET");
        final PageDto actualPageDto = new PageDto(pageIndex, pageSize, nextPageClick, prevPageClick);
        final PageDto updatedPageDto = trainService.findTrains(actualPageDto, departureDate, departureTime, goingTo);
        log.info(actualPageDto);

        model.addAttribute(Attributes.PAGE_DTO, updatedPageDto);
        model.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        model.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        model.addAttribute(Attributes.GOING_TO, goingTo);

        return view.index;
    }

    @GetMapping("/bookingPage")
    public String bookingPage(@RequestParam Long trainId, Model model) {
        final TrainDto trainDto = trainService.getTrainDtoById(trainId);
        log.info(trainDto);

        model.addAttribute(Attributes.TRAIN, trainDto);
        model.addAttribute(Attributes.TRAIN_BOOKING, new TrainBookingDto());
        return view.trainBooking;
    }
}
