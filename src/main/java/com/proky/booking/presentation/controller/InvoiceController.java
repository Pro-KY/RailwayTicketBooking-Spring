package com.proky.booking.presentation.controller;

import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@AllArgsConstructor
public class InvoiceController {
    private View view;

    @PostMapping("/invoice")
    public String bookingPage(@ModelAttribute TrainBookingDto ticketBooking) {
        log.info(ticketBooking);
        return "redirect:" + view.invoice;
    }
}
