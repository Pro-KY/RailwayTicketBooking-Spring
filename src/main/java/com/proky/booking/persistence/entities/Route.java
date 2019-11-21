package com.proky.booking.persistence.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "route", schema = "railway_ticket_booking")
public class Route {
    private Long id;
    private String name;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "departure_date")
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Basic
    @Column(name = "arrival_date")
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Basic
    @Column(name = "departure_time")
    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "arrival_time")
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "route_length_factor")
    public Double getRouteLengthFactor() {
        return routeLengthFactor;
    }

    public void setRouteLengthFactor(Double routeLengthFactor) {
        this.routeLengthFactor = routeLengthFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) &&
                Objects.equals(name, route.name) &&
                Objects.equals(departureDate, route.departureDate) &&
                Objects.equals(arrivalDate, route.arrivalDate) &&
                Objects.equals(departureTime, route.departureTime) &&
                Objects.equals(arrivalTime, route.arrivalTime) &&
                Objects.equals(routeLengthFactor, route.routeLengthFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departureDate, arrivalDate, departureTime, arrivalTime, routeLengthFactor);
    }
}
