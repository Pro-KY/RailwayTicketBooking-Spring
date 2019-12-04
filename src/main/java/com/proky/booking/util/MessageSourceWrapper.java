package com.proky.booking.util;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;

import java.util.Locale;

@Log4j2
@AllArgsConstructor
public class MessageSourceWrapper {
    private MessageSource messageSource;

    public String getMessage(String key, @Nullable Object[] params) {
        final Locale locale = LocaleContextHolder.getLocale();
        log.info(locale);
        return messageSource.getMessage(key, params, locale);
    }

    public String getMessage(String key) {
        final Locale locale = LocaleContextHolder.getLocale();
        log.info(locale);
        return messageSource.getMessage(key, null, locale);
    }
}
