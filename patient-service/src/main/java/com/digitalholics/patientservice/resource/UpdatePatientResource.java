package com.digitalholics.patientservice.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientResource {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String photoUrl;

    private String birthday;

    private String appointmentQuantity;

    private String location;
}
