package com.proky.booking.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:view.properties")
@Getter
@Setter
public class ViewProperties {
    @Value("${index}")
    private String indexPage;

    @Value("${signIn}")
    String signInPage;

    @Value("${signUp}")
    String signUpPage;

}
