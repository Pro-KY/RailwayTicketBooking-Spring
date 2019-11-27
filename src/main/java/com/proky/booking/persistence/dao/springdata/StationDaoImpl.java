package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.IStationDao;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class StationDaoImpl implements IStationDao {
    private StationRepository stationRepository;


    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station save(Station entity) {
        return stationRepository.save(entity);
    }

    @Override
    public Station update(Station entity) {
        return stationRepository.save(entity);
    }

    @Override
    public void delete(Station entity) {
        stationRepository.delete(entity);
    }

    @Override
    public Optional<Station> findById(Long id) {
        return stationRepository.findById(id);
    }
}
