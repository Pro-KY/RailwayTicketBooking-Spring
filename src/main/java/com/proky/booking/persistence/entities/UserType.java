package com.proky.booking.persistence.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_type", schema = "railway_ticket_booking_spring")
public class UserType {
    private Long id;
    private String type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return Objects.equals(id, userType.id) &&
                Objects.equals(type, userType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
