package com.app.pharmacy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDataDto {
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String role;

}
