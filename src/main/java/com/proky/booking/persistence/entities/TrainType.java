package com.proky.booking.persistence.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "train_type", schema = "railway_ticket_booking")
public class TrainType {
    private Long id;
    private String type;
    private BigDecimal seatPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "seat_price")
    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainType trainType = (TrainType) o;
        return Objects.equals(id, trainType.id) &&
                Objects.equals(type, trainType.type) &&
                Objects.equals(seatPrice, trainType.seatPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, seatPrice);
    }
}
