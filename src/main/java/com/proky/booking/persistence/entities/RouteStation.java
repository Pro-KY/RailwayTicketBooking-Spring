package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "route_station", schema = "railway_ticket_booking_spring")
@NoArgsConstructor
@Getter
@Setter
public class RouteStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    public RouteStation(Route route, Station station) {
        this.route = route;
        this.station = station;
    }

    @Override
    public String toString() {
        return "RouteStation{" +
                "id=" + id +
                ", station=" + station +
                '}';
    }
}
