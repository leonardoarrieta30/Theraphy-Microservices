package com.digitalholics.patientservice.domain.model.entity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String type;
}
