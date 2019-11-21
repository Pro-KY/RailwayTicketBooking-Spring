package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "route", schema = "railway_ticket_booking")
@Data
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "arrival_date")
    private Date arrivalDate;
    @Column(name = "departure_time")
    private Time departureTime;
    @Column(name = "arrival_time")
    private Time arrivalTime;
    @Column(name = "route_length_factor")
    private Double routeLengthFactor;

    @OneToMany(
            mappedBy = "station",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RouteStation> stations = new ArrayList<>();

    public void addStation(Station station) {
        RouteStation routeStation = new RouteStation(this, station);
        stations.add(routeStation);
        station.getRoutes().add(routeStation);
    }

    public void removeStation(Station station) {
        for (Iterator<RouteStation> iterator = stations.iterator(); iterator.hasNext(); ) {
            RouteStation routeStation = iterator.next();

            if (routeStation.getRoute().equals(this) && routeStation.getStation().equals(station)) {
                iterator.remove();
                routeStation.getStation().getRoutes().remove(routeStation);
                routeStation.setRoute(null);
                routeStation.setStation(null);
            }
        }
    }
}
