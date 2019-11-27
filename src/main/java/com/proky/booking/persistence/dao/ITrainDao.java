package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ITrainDao extends IDao<Train> {
    Page<Train> findTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station, Pageable pageable);
}
