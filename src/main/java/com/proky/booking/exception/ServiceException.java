package com.proky.booking.exception;
import org.springframework.stereotype.Component;

import static com.proky.booking.util.constans.enums.ExceptionsEnum.SERVICE_EXCEPTION;

@Component
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(SERVICE_EXCEPTION + message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
