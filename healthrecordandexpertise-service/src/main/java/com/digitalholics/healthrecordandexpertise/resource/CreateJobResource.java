package com.digitalholics.healthrecordandexpertise.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobResource {
    private Integer physiotherapistId;
    private String position;
    private String organization;
}
