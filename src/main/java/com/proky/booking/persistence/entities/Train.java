package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "train", schema = "railway_ticket_booking_spring")
@NoArgsConstructor
@Setter
@Getter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TrainType trainType;

    @OneToOne
    private Route route;

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                "route=" + route +
                '}';
    }
}
