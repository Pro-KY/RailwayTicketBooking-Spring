package com.proky.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
   private String id;
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private String userTypeId;
}
