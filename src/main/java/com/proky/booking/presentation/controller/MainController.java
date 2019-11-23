package com.proky.booking.presentation.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@Controller
public class MainController {

    @RequestMapping(path = {"/", "index"})
    public String indexPage() {
        log.info("get index page");
        return "index";
    }
}
