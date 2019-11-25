package com.proky.booking.configuration;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.entities.Train;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.*;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(PropertyMap<Train, TrainDto> trainDtoMapper) {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(trainDtoMapper);
        return modelMapper;
    }

    @Bean
    public PropertyMap<Train, TrainDto> trainDtoMapper() {
         return new PropertyMap<Train, TrainDto>() {
            @Override
            protected void configure() {
                map().setTrainId(source.getId());
                map().setArrivalStationName(source.getRoute().getArrivalStation().getName());
                map().setDepartureStationName(source.getRoute().getDepartureStation().getName());
            }
        };
    }

}