package com.proky.booking.presentation.controller;

import com.proky.booking.dto.SecureUserDto;
import com.proky.booking.dto.InvoiceDto;
import com.proky.booking.dto.TrainBookingDto;
import com.proky.booking.service.InvoiceService;
import com.proky.booking.service.security.SecurityService;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@AllArgsConstructor
public class InvoiceController {
    private ViewPath viewPath;
    private InvoiceService invoiceService;
    private SecurityService securityService;

    @PostMapping(value = "/invoice")
    public String calculateInvoice(@ModelAttribute TrainBookingDto trainBooking, Model model) {
        final InvoiceDto invoiceDto = invoiceService.calculateInvoice(trainBooking);
        log.info(invoiceDto);

        final boolean notAnonymousUser = !securityService.isAnonymousUser();

        if (notAnonymousUser) {
            final SecureUserDto secureUserDto = securityService.getCurrentUser();
            final Long userId = secureUserDto.getUserId();
            log.info("userId {}", userId);
            invoiceDto.setUserId(userId);
            invoiceService.saveInvoice(invoiceDto);
        }

        model.addAttribute(Attributes.INVOICE_DTO, invoiceDto);
        return viewPath.invoice;
    }
}
