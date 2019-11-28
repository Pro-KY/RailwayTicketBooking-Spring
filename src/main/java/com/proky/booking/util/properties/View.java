package com.proky.booking.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:view.properties")
public class View {
    @Value("${index}")
    public String index;

    @Value("${signIn}")
    public String signIn;

    @Value("${signUp}")
    public String signUp;

    @Value("${adminUsers}")
    public String adminUsers;

}
