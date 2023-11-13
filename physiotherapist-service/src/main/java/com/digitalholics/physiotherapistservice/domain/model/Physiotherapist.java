package com.digitalholics.physiotherapistservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physiotherapists")
public class Physiotherapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dni;

    private String specialization;

    private Integer age;

    private String location;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "birthday_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthdayDate;

    private Double rating;

    @Column(name = "consultation_quantity")
    private Integer consultationQuantity;

    @Column(name = "patient_quantity")
    private Integer patientQuantity;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    //    @OneToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;

    private Double fees;

    @Column(name = "user_id")
    private Integer userId;



    
}
