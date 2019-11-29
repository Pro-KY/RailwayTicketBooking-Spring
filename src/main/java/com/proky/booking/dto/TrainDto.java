package com.proky.booking.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto implements Serializable {
    private Long trainId;
    private String trainType;
    private BigDecimal trainSeatPrice;

    private Long routeId;

    private Long departureStationId;
    private Long arrivalStationId;

    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private Double routeLengthFactor;

    private List<StationDto> stations;


}
