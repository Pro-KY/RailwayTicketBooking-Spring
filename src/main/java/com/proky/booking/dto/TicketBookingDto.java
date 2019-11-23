package com.proky.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingDto implements Serializable {
    private String trainId;
    private String firstName;
    private String lastName;
    private String seatsAmount;
}
