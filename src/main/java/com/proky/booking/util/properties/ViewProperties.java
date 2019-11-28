package com.proky.booking.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:view.properties")
public class ViewProperties {
    @Value("${index}")
    public String indexView;

    @Value("${signIn}")
    public String signInView;

    @Value("${signUp}")
    public String signUpView;

    @Value("${adminUsers}")
    public String adminUsersView;

}
