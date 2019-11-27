package com.proky.booking.persistence.dao.springdata;

import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.persistence.repository.TrainRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TrainDaoImpl implements ITrainDao {
    private TrainRepository trainRepository;

    @Override
    public Page<Train> findTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station, Pageable pageable) {
        return trainRepository.findAllByDepartureDateAndDepartureTimeAndStation(departureDate, departureTime, station, pageable);
    }

    @Override
    public Train save(Train entity) {
        return trainRepository.save(entity);
    }

    @Override
    public Train update(Train entity) {
        return trainRepository.save(entity);
    }

    @Override
    public void delete(Train entity) {
        trainRepository.delete(entity);
    }

    @Override
    public Optional<Train> findById(Long id) {
        return trainRepository.findById(id);
    }
}
