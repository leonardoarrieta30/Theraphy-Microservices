package com.digitalholics.appointmentservice.domain.service;

import com.digitalholics.appointmentservice.domain.model.entity.Appointment;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Theraphy2;
import com.digitalholics.appointmentservice.feignClients.TherapyFeignClient;
import com.digitalholics.appointmentservice.resource.CreateAppointmentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {


    
    Page<Appointment> getAll(Pageable pageable);
    List<Appointment> getAll();
    

    Appointment getById(Integer appointmentId);

    //Appointment getAppointmentByTheraphyId(Integer theraphyId);

    Appointment create(CreateAppointmentResource appointment, Integer therapyId);

    ResponseEntity<?> delete(Integer appointmentId);
    Theraphy2 getTherapyByAppointmentId(Integer appointmentId);

    List<Appointment> getAppointmentsByTherapyId(Integer therapyId);
    Appointment update(Integer appointmentId, Appointment request);

    Theraphy2 getTherapyById(Integer patientId);
   // Theraphy2 saveTherapy(Integer appointmentId, Theraphy2 theraphy2);

}
