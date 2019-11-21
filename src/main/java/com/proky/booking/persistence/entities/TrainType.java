package com.proky.booking.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "train_type", schema = "railway_ticket_booking")
@Data
@NoArgsConstructor
public class TrainType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "seat_price")
    private BigDecimal seatPrice;

}
