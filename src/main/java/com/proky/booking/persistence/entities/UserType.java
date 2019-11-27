package com.proky.booking.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "user_type", schema = "railway_ticket_booking_spring")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "type")
    private String type;
}
