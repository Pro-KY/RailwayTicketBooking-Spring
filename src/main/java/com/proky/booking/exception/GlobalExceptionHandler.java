package com.proky.booking.exception;

import com.proky.booking.dto.ErrorDto;
import com.proky.booking.service.security.SecurityService;
import com.proky.booking.util.constans.Attributes;
import com.proky.booking.util.constans.RoleEnum;
import com.proky.booking.util.constans.ViewPath;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private ViewPath viewPath;
    SecurityService securityService;

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleAllExceptions(Throwable ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(viewPath.errorRuntime);

        log.info("handle all exceptions");
        ex.printStackTrace();

        final String message = ex.getMessage();
        final String exName = ex.getClass().getName();
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_NAME, exName);
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_MSG, message);

        return modelAndView;
    }
}
