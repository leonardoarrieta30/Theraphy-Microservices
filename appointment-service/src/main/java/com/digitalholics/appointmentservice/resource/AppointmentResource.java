package com.digitalholics.appointmentservice.resource;



import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResource {
    private Integer id;
    private Boolean done;
    private String topic;
    private String diagnosis;
    private String date;
    private String hour;
    private String place;
    private Integer patientId;
    private Integer physiotherapistId;
}
