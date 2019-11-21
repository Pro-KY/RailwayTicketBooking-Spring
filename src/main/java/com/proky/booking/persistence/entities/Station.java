package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "station", schema = "railway_ticket_booking")
@Data
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "route",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RouteStation> routes = new ArrayList<>();


    public List<RouteStation> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteStation> routes) {
        this.routes = routes;
    }
}
