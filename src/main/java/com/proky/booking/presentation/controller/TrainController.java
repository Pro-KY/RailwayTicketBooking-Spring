package com.proky.booking.presentation.controller;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.service.TrainService;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
@Log4j2
@RequestMapping("/trains")
@Controller
@AllArgsConstructor
public class TrainController {
    private ViewPath viewsPath;
    private TrainService trainService;

    @PostMapping("/find")
    public String findTrain(@RequestParam @NotBlank @Size(min = 1, max = 3)String goingTo,
        @RequestParam @NotBlank @Length(min = 10, max = 11) String departureDate,
        @RequestParam @NotBlank @Length(min = 7, max = 8) String departureTime,
        RedirectAttributes attributes) {

        final PageDto actualPageDto = new PageDto();
        final PageDto updatedPageDto = trainService.findTrains(actualPageDto, departureDate, departureTime, goingTo);

        attributes.addFlashAttribute(Attributes.PAGE_DTO, updatedPageDto);
        attributes.addFlashAttribute(Attributes.DEPARTURE_DATE, departureDate);
        attributes.addFlashAttribute(Attributes.DEPARTURE_TIME, departureTime);
        attributes.addFlashAttribute(Attributes.GOING_TO, goingTo);

        return "redirect:/trains";
    }

    @GetMapping("/find")
    public String findTrainsUsingPagination(
        @RequestParam Integer pageSize,
        @RequestParam(required = false) Boolean prevPageClick,
        @RequestParam(required = false) Boolean nextPageClick,
        @RequestParam(required = false) Integer pageIndex,
        @RequestParam String goingTo,
        @RequestParam String departureDate,
        @RequestParam String departureTime,
        Model model) {

        final PageDto actualPageDto = new PageDto(pageIndex, pageSize, nextPageClick, prevPageClick);
        final PageDto updatedPageDto = trainService.findTrains(actualPageDto, departureDate, departureTime, goingTo);

        model.addAttribute(Attributes.PAGE_DTO, updatedPageDto);
        model.addAttribute(Attributes.DEPARTURE_DATE, departureDate);
        model.addAttribute(Attributes.DEPARTURE_TIME, departureTime);
        model.addAttribute(Attributes.GOING_TO, goingTo);

        return viewsPath.index;
    }

    @GetMapping("/")
    public String trainBookingPage(@RequestParam Long trainId, Model model) {
        final TrainDto trainDto = trainService.getTrainDtoById(trainId);
        log.info(trainDto);

        model.addAttribute(Attributes.TRAIN, trainDto);
        model.addAttribute(Attributes.TRAIN_BOOKING, new TrainBookingDto());
        return viewsPath.trainBooking;
    }
}
