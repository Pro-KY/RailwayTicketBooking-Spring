package com.proky.booking.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDto implements Serializable {
   private String id;
   private String firstName;
   private String lastName;
   @NonNull private String email;
   @NonNull private String password;
   private String newPassword;
   private String userTypeId;
   private boolean isAdministrator;
}
