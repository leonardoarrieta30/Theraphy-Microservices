package com.digitalholics.therapyservice.domain.service;


import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.domain.model.entity.dto.Appointment;
import com.digitalholics.therapyservice.resource.CreateTheraphyResource;
import com.digitalholics.therapyservice.resource.TheraphyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TheraphyService {

    Theraphy create(CreateTheraphyResource theraphy);

    ResponseEntity<?> delete(Integer theraphyId);
    
    List<Theraphy> getAll();

    Page<Theraphy> getAll(Pageable pageable);

   // Theraphy byAppointmentId(Integer appointmentId);
    Theraphy getTherapyByPhysiotherapistId(Integer physiotherapistId);
    List<Theraphy> getTherapiesByPhysiotherapistId(Integer physiotherapistId);

    List<Theraphy> getTherapiesByPatientId(Integer patientId);

    //Theraphy byPatientId(Integer patientId);

   // List<Appointment> getAppointmentsByTherapyId(Integer therapyId);


   // List<Theraphy> getTherapyByPatientId(@PathVariable("patientId") Integer patientId);
    Theraphy getById(Integer theraphyId);

    Theraphy update(Integer theraphyId, Theraphy request);

}
