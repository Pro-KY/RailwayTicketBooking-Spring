package com.proky.booking.configuration;

import com.proky.booking.service.PaginationService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@PropertySources({
        @PropertySource("classpath:view.properties")
})
public class ApplicationConfiguration {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PaginationService paginationService() {
        return new PaginationService();
    }
}