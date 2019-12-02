package com.proky.booking.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:view.properties")
public class ViewPath {
    @Value("${index}")
    public String index;

    @Value("${signIn}")
    public String signIn;

    @Value("${deleteUser}")
    public String deleteUser;

    @Value("${updateUser}")
    public String updateUser;

    @Value("${manageUser}")
    public String manageUser;

    @Value("${signUp}")
    public String signUp;

    @Value("${allUsers}")
    public String allUsers;

    @Value("${trainBooking}")
    public String trainBooking;

    @Value("${invoice}")
    public String invoice;

    @Value("${errorRuntime}")
    public String errorRuntime;

    @Value("${accessDenied}")
    public String accessDenied;
}
