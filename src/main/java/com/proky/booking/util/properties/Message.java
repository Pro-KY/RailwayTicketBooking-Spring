package com.proky.booking.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Message {
    @Value("${notFoundEntity}")
    public String notFoundEntity;

    @Value("${userCreated}")
    public String userCreated;

    @Value("${authorizationError}")
    public String authorizationError;

    @Value("${userDeleted}")
    public String userDeleted;

    @Value("${userUpdated}")
    public String userUpdated;

    @Value("${userExist}")
    public String userExist;
}
