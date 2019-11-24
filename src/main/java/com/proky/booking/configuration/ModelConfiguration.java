package com.proky.booking.configuration;

import com.proky.booking.service.PaginationService;
import com.proky.booking.util.TrainMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
public class ModelConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PaginationService paginationService() {
        return new PaginationService();
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new TrainMapper());
        return modelMapper;
    }

}