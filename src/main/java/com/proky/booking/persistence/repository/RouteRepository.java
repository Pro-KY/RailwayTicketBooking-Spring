package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findById(Long id);

    List<Route> findAllByArrivalTimeIsLessThanEqual(Date time);

    @Query("select r from Route r where r.departureDate = :departureDate and r.departureTime <= :departureTime and r.arrivalTime <= :departureTime")
    List<Route> findRoutes(@Param("departureDate") Date departureDate, @Param("departureTime") Date departureTime);

//    @Query("select r from Route r where r.departureDate = :departureDate")
//    List<Route> findRoutes(@Param("departureDate") Date departureDate);

    @Query("select r from Route r where r.departureTime <= :departureTime and r.arrivalTime <= :departureTime")
    List<Route> findRoutes(@Param("departureTime") Date departureTime);

}
