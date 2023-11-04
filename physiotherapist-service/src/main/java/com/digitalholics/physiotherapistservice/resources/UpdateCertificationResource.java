package com.digitalholics.physiotherapistservice.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCertificationResource {
    private Integer id;
    private Integer physiotherapistId;
    private String title;
    private String school;
    private Integer year;
    private String photoUrl;
}
