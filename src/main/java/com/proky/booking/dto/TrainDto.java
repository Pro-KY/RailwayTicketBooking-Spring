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
    private Integer trainId;
    private String trainType;
    private BigDecimal trainSeatPrice;

    private Long routeId;
//    private String routeName;

    String departureStationName;
    String arrivalStationName;

    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private Double routeLengthFactor;

    private List<StationDto> stations;


}
