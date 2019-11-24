package com.proky.booking.util;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.entities.Train;
import org.modelmapper.PropertyMap;

public class TrainMapper extends PropertyMap<Train, TrainDto> {
    @Override
    protected void configure() {
        map().setArrivalStationName(source.getRoute().getArrivalStation().getName());
        map().setDepartureStationName(source.getRoute().getDepartureStation().getName());
    }
}
