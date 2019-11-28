package com.proky.booking.config;

import com.proky.booking.service.PaginationService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources({
        @PropertySource("classpath:view.properties"),
        @PropertySource("classpath:msg.properties")
})
public class ApplicationConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PaginationService paginationService() {
        return new PaginationService();
    }
}