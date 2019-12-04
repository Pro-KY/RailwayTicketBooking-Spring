package com.proky.booking.presentation.controller;

import com.proky.booking.util.constans.Attributes;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@SessionAttributes(Attributes.LANGUAGE)
public class LanguageController {
    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam(defaultValue = "en") String language,
                                 @RequestHeader(value = "referer", required = false) final String referer,
                                 Model model) {
        log.info("changing language, selected lang is: {}", language);
        log.info("referer is {}", referer);
        model.addAttribute(Attributes.LANGUAGE, language);
        return "redirect:" + referer;
    }
}
