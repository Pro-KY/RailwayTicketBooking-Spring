package com.proky.booking.service;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.repository.StationRepository;
import com.proky.booking.util.properties.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StationService {
    Message message;
    private StationRepository stationRepository;

    public List<Station> findAllStations() {
        return stationRepository.findAll();
    }

    Station findById(Long id) {
//        return stationRepository.findById(id).orElseThrow(() -> new ServiceException(messageProperties.getNOT_FOUND_ENTITY()));
        return null;
    }

}
