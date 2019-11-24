package com.proky.booking.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationDto implements Serializable {
    private Long stationId;
    private String stationName;

    @Override
    public String toString() {
       return stationName;
    }
}
