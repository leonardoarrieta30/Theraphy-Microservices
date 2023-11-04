package com.digitalholics.physiotherapistservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "physiotherapist_id")
    private Integer physiotherapistId;

    @Size(max = 50)
    private String title;

    @Size(max = 50)
    private String school;

    private Integer year;

    @Column(name = "photo_url")
    private String photoUrl;



}
