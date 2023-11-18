package com.digitalholics.appointmentservice.domain.model.entity.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    private Integer id;
    private String dni;
    private String photoUrl;
    private String birthday;
    private String location;
    private Integer userId;
}
