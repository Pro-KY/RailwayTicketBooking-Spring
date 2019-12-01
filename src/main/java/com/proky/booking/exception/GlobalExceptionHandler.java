package com.proky.booking.exception;

import com.proky.booking.dto.ErrorDto;
import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private ViewPath viewPath;

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleAllExceptions(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView(viewPath.errorRuntime);

        log.debug("handle all exceptions");
        final String message = ex.getMessage();
        final String exName = ex.getClass().getName();
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_NAME, exName);
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_MSG, message);

        return modelAndView;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceExceptions(ServiceException ex, @RequestHeader(value = "referer", required = false) final String referer) {
        log.debug("handle service layer exception");

        ModelAndView modelAndView = new ModelAndView(referer);
        modelAndView.addObject(Attributes.ALERT_ERROR, true);
        modelAndView.addObject(Attributes.ALERT_MESSAGE, ex.getMessage());

        return modelAndView;
    }
}
