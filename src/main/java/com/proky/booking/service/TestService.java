package com.proky.booking.service;

import com.proky.booking.persistence.entities.Route;
import com.proky.booking.persistence.entities.Station;
import com.proky.booking.persistence.entities.Train;
import com.proky.booking.persistence.repository.RouteRepository;
import com.proky.booking.persistence.repository.TrainRepository;
import com.proky.booking.util.SqlDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class TestService {
    private RouteRepository routeRepository;
    private TrainRepository trainRepository;
    private SqlDateTimeConverter sqlDateTimeConverter;


    @Transactional
    public void testRoute() {
//        final Optional<Route> byId = routeRepository.findById(6L);
//        final Route route = byId.orElse(new Route());
//        final List<RouteStation> stations = route.getStations();
//        stations.forEach(station -> log.info(station.toString()));
//        trainRepository.findAllTrainsWithPagination();

        final Station station1 = new Station(1L, "Киев");
        final Date date = sqlDateTimeConverter.convertToSqlDate("10/31/2019");
        final Time time = sqlDateTimeConverter.convertToSqlTime("9:15 PM");

        Pageable pageable = PageRequest.of(1, 3);
//        final Page<Train> allTrainsWithPagination = trainRepository.findAllByDepartureDateAndDepartureTimeAndStation(date, time, 1L, pageable);
        final Page<Train> allTrainsWithPagination = trainRepository.findAllByDepartureDateAndDepartureTimeAndStation(date, time, station1, pageable);
        final List<Train> content = allTrainsWithPagination.getContent();
        content.forEach(train -> log.info(train.toString()));

        log.info(allTrainsWithPagination.getTotalElements());
        log.info(allTrainsWithPagination.getTotalPages());


//        final Optional<Route> byId = routeRepository.findById(8L);
//        final Route route = byId.orElse(new Route());
//        final List<Station> stations = route.getStations();
//        stations.forEach(station -> log.info(station.toString()));
    }

    @Transactional
    public void test_2() {
        final Date date = sqlDateTimeConverter.convertToSqlDate("10/31/2019");
        final Time time = sqlDateTimeConverter.convertToSqlTime("9:15 PM");
//        final List<Route> allByDepartureTimeIsLessThan = routeRepository.findAllByArrivalTimeIsLessThanEqual(time);

//        final List<Route> allByDepartureTimeIsLessThan = routeRepository.findRoutes(date, time);
        final List<Route> allByDepartureTimeIsLessThan = routeRepository.findRoutes(time);
        for (Route route : allByDepartureTimeIsLessThan) {
            System.out.println(route.toString());
        }
        System.out.println(allByDepartureTimeIsLessThan.size());
    }
}
