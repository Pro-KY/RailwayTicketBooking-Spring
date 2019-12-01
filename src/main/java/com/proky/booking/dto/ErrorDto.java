package com.proky.booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ErrorDto implements Serializable {
    private String requestURI;
    private String servletName;
    private Integer statusCode;
    private String exceptionName;
    private String exceptionMessage;

    public ErrorDto(HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (throwable != null) {
            exceptionName = throwable.getClass().getName();
            exceptionMessage = throwable.getMessage();
        }

        statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }

        requestURI = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestURI == null) {
            requestURI = "Unknown";
        }
    }
}
