package com.proky.booking.service;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> findAllStations() {
        return stationRepository.findAll();
    }

}
