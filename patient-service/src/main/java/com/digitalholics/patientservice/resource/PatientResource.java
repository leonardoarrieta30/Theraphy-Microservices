package com.digitalholics.patientservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatientResource {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String photoUrl;

    private String birthday;

    private String appointmentQuantity;

    private String location;
}
