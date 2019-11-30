package com.proky.booking.presentation.controller;

import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.dto.UserDto;
import com.proky.booking.service.InvoiceService;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@Controller
@AllArgsConstructor
public class InvoiceController {
    private View view;
    private InvoiceService invoiceService;

    @PostMapping(value = "/invoice")
    public String calculateInvoice(@ModelAttribute TrainBookingDto trainBooking, @SessionAttribute(required = false) UserDto user, Model model) {
        final InvoiceDto invoiceDto = invoiceService.calculateInvoice(trainBooking);
        final boolean isUserPresent = Objects.nonNull(user);

        if (isUserPresent) {
            invoiceDto.setUserId(Long.parseLong(user.getId()));
            invoiceService.saveInvoice(invoiceDto);
        }

        String firstName = isUserPresent ? user.getFirstName() : trainBooking.getFirstName();
        String lastName = isUserPresent ? user.getLastName() : trainBooking.getLastName();
        invoiceDto.setUserFirstName(firstName);
        invoiceDto.setUserLastName(lastName);

        model.addAttribute(Attributes.INVOICE_DTO, invoiceDto);
        return view.invoice;
    }
}
