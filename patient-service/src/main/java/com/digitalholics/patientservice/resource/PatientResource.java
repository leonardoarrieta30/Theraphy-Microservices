package com.digitalholics.patientservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PatientResource {
    private Integer id;
    private String dni;
    private String photoUrl;
    private String birthday;
    private String appointmentQuantity;
    private String location;
    private Integer userId;
}
