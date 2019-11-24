package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query(value = "select t from Train t join t.route r join r.departureStation join r.arrivalStation join r.stations s " +
        "where r.departureDate = :departureDate and r.departureTime <= :departureTime and r.arrivalTime <= :departureTime and s = :station and r.departureStation <> :station " +
        "order by t.id asc")
    Page<Train> findAllByDepartureDateAndDepartureTimeAndStation(@Param("departureDate") Date departureDate, @Param("departureTime") Date departureTime, @Param("station") Station station, Pageable pageable);

}
