package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

@Entity
@Table(name = "route", schema = "railway_ticket_booking_spring")
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_station_id")
    private Station departureStation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_station_id")
    private Station arrivalStation;

    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "departure_time")
    private Date departureTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "route_length_factor")
    private Double routeLengthFactor;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "route_station",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private List<Station> stations = new ArrayList<>();

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", routeLengthFactor=" + routeLengthFactor +
                '}';
    }


    //    public void addStation(Station station) {
//        RouteStation routeStation = new RouteStation(this, station);
//        stations.add(routeStation);
//        station.getRoutes().add(routeStation);
//    }
//
//    public void removeStation(Station station) {
//        for (Iterator<RouteStation> iterator = stations.iterator(); iterator.hasNext(); ) {
//            RouteStation routeStation = iterator.next();
//
//            if (routeStation.getRoute().equals(this) && routeStation.getStation().equals(station)) {
//                iterator.remove();
//                routeStation.getStation().getRoutes().remove(routeStation);
//                routeStation.setRoute(null);
//                routeStation.setStation(null);
//            }
//        }
//    }
}
