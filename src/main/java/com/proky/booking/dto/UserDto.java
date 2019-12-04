package com.proky.booking.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {
   private String id;

   @NotBlank
   @Size(min=2, max=50)
   private String firstName;

   @NotBlank
   @Size(min=2, max=50)
   private String lastName;

   @NotBlank
   @Email
   private String email;

   @NotBlank
   @Size(min=6, max=80)
   private String password;

   private String newPassword;
   private String userTypeId;
   private boolean isAdministrator;
}
