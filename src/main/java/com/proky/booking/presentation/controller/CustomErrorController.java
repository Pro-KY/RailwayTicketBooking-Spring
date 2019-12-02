package com.proky.booking.presentation.controller;

import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@AllArgsConstructor
public class CustomErrorController implements ErrorController {
    private ViewPath viewPath;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        log.info("handleError =>");

        String errorViewPath = viewPath.errorRuntime;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorViewPath =  viewPath.errorNotFound;
            }
        }

        return errorViewPath;
    }

    @Override
    public String getErrorPath() {
        return viewPath.errorRuntime;
    }

}
