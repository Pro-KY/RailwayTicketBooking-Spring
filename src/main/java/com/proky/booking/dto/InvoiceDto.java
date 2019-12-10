package com.proky.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto implements Serializable {
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long trainId;
    private String trainType;

    private Long departureStationId;
    private Long arrivalStationId;

    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private BigDecimal seatsAmount;
    private BigDecimal sum;
}
