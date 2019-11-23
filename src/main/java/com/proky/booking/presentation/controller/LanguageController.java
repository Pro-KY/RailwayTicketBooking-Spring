package com.proky.booking.presentation.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class LanguageController {

    @GetMapping("/changeLanguage")
    public String chnageLanguage(@RequestParam(name="language", required=false, defaultValue="en") String language, Model model) {
        log.info("language is {}", language);

        return "index";
    }
}
