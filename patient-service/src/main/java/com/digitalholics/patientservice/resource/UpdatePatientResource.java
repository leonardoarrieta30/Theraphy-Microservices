package com.digitalholics.patientservice.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientResource {
    private Integer id;
    private String dni;
    private String photoUrl;
    private String birthday;
    private String location;
}
