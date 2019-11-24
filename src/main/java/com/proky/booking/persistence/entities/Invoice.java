package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "invoice", schema = "railway_ticket_booking_spring")
@Data
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seats_amount")
    private Integer seatsAmount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_time")
    private Timestamp dateTime;

    @OneToOne
    private Train train;

    @OneToOne
    private User user;

}
