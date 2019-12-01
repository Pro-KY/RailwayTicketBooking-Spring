package com.proky.booking.exception;

import com.proky.booking.util.constans.http.Attributes;
import com.proky.booking.util.properties.View;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private View view;

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleAllExceptions(HttpServletRequest request, Exception ex) {
        final ErrorData errorData = new ErrorData(request);

        ModelAndView modelAndView = new ModelAndView(view.errorRuntime);
        modelAndView.addObject(Attributes.ERROR_REQUEST_URI, errorData.getRequestURI());
        modelAndView.addObject(Attributes.ERROR_SERVLET_NAME, errorData.getServletName());
        modelAndView.addObject(Attributes.ERROR_STATUS_CODE, errorData.getStatusCode());
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_NAME, errorData.getExceptionName());
        modelAndView.addObject(Attributes.ERROR_EXCEPTION_MSG, errorData.getExceptionMessage());
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "page not found")
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleNotFoundCase() {
        return new ModelAndView(view.errorNotFound);
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
