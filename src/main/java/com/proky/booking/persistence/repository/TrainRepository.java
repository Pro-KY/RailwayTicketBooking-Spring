package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long>, PagingAndSortingRepository<Train, Long> {

//    @Query(value = "select * from train t " +
//            "inner join route r on t.route_id = r.id " +
//            "inner join route_station rs on r.id = rs.route_id " +
//            "where r.departure_date = :departureDate and r.departure_time <= :departureTime and r.arrival_time <= :departureTime and rt.station = :station " +
//            "and rt.station <> (select rt.station_id from route_station rt where rt.route_id = t.route_id order by rt.id ASC limit 0, 1) ", nativeQuery = true)
//    Page<Train> findAllTrainsWithPagination(Date departureDate, Time departureTime, Station station, Pageable pageable);

}
