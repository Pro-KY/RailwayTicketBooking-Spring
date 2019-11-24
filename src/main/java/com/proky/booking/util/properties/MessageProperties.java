package com.proky.booking.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Component
@PropertySource("classpath:msg.properties")
@Getter
@Setter
public class MessageProperties {
    private  final String fileName = "message";
    private  final String USER_EXIST = "USER_EXIST";
    private  final String NOT_VALID_PROPERTY = "NOT_VALID_PROPERTY";

    @Value("${NOT_FOUND_ENTITY}")
    private  final String NOT_FOUND_ENTITY = "NOT_FOUND_ENTITY";

    private  final String TRANSACTION_ERROR = "TRANSACTION_ERROR";
    private  final String AUTHORIZATION_ERROR = "AUTHORIZATION_ERROR";
    private  final String USER_UPDATED = "USER_UPDATED";
    private  final String USER_DELETED = "USER_DELETED";
    private  final String USER_CREATED = "USER_CREATED";

}
