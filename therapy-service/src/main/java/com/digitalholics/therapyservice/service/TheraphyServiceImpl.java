package com.digitalholics.therapyservice.service;


import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.domain.model.entity.dto.Appointment;
import com.digitalholics.therapyservice.domain.model.entity.dto.Patient;
import com.digitalholics.therapyservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.therapyservice.domain.persistence.TheraphyRepository;
import com.digitalholics.therapyservice.domain.service.TheraphyService;
import com.digitalholics.therapyservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.therapyservice.mapping.Exception.ResourceValidationException;
import com.digitalholics.therapyservice.resource.CreateTheraphyResource;
import com.digitalholics.therapyservice.resource.TheraphyResource;
import com.digitalholics.therapyservice.resource.UpdateTheraphyResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class TheraphyServiceImpl implements TheraphyService {


    private static final String ENTITY = "Theraphy";

    private final TheraphyRepository theraphyRepository;



    private final RestTemplate restTemplate;

    private final Validator validator;


    @Autowired
    public TheraphyServiceImpl(TheraphyRepository theraphyRepository, Validator validator, RestTemplate restTemplate) {
        this.theraphyRepository = theraphyRepository;
        this.validator = validator;
        this.restTemplate = restTemplate;
    }



    @Override
    public List<Theraphy> getAll() {
        return theraphyRepository.findAll();
    }

    @Override
    public Page<Theraphy> getAll(Pageable pageable) {
        return theraphyRepository.findAll(pageable);
    }

    @Override
    public Theraphy getById(Integer theraphyId) {
        return theraphyRepository.findById(theraphyId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, theraphyId));
    }

    @Override
    public Theraphy create(CreateTheraphyResource theraphyResource) {
       Set<ConstraintViolation<CreateTheraphyResource>> violations = validator.validate(theraphyResource);

       if(!violations.isEmpty())
           throw new ResourceValidationException(ENTITY, violations);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();

        Theraphy theraphy = new Theraphy();
        theraphy.setTheraphyName(theraphyResource.getTheraphyName());
        theraphy.setAppointmentQuantity(theraphyResource.getAppointmentQuantity());
        theraphy.setAppointmentGap(theraphyResource.getAppointmentGap());
        theraphy.setStartAt(theraphyResource.getStartAt());
        theraphy.setFinishAt(theraphyResource.getFinishAt());

        if(this.getPatientById(theraphyResource.getPatientId()) == null){
            throw new ResourceNotFoundException("Patient not found");
        }else{
            theraphy.setPatientId(theraphyResource.getPatientId());
        }
        if(this.getPhysiotherapistById(theraphyResource.getPhysiotherapistId()) == null) {
            throw new ResourceNotFoundException("Physiotherapist not found");
        }else{
            theraphy.setPhysiotherapistId(theraphyResource.getPhysiotherapistId());
        }

       return theraphyRepository.save(theraphy);
    }

    @Override
    public Theraphy update(Integer Theraphy, UpdateTheraphyResource theraphyResource) {
        Theraphy theraphy = getById(Theraphy);

        theraphy.setTheraphyName(theraphyResource.getTheraphyName() != null ? theraphyResource.getTheraphyName() : theraphy.getTheraphyName());
        theraphy.setStartAt(theraphyResource.getStartAt() != null ? theraphyResource.getStartAt() : theraphy.getStartAt());
        theraphy.setAppointmentGap(theraphyResource.getAppointmentGap() != null ? theraphyResource.getAppointmentGap() : theraphy.getAppointmentGap());
        theraphy.setFinishAt(theraphyResource.getFinishAt() != null ? theraphyResource.getFinishAt() : theraphy.getFinishAt());
        theraphy.setAppointmentQuantity(theraphyResource.getAppointmentQuantity() != null ? theraphyResource.getAppointmentQuantity() : theraphy.getAppointmentQuantity());
        //theraphy.setPatientId(this.getPatientById(theraphyResource.getPatientId()) != null ? theraphyResource.getPatientId() : theraphy.getPatientId());
        //theraphy.setPhysiotherapistId(this.getPhysiotherapistById(theraphyResource.getPhysiotherapistId()) != null ? theraphyResource.getPhysiotherapistId() : theraphy.getPhysiotherapistId());

        return theraphyRepository.save(theraphy);



    }

    @Override
    public ResponseEntity<?> delete(Integer theraphyId) {
        return theraphyRepository.findById(theraphyId)
                .map(theraphy -> {
                    theraphyRepository.delete(theraphy);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, theraphyId));
    }

//    public Theraphy byAppointmentId(Integer appointmentId) {
//        return theraphyRepository.findByAppointmentId(appointmentId);
//    }


    public Theraphy getTherapyByPhysiotherapistId(Integer physiotherapistId) {
        return theraphyRepository.findTheraphyByPhysiotherapistId(physiotherapistId);
    }


    public List<Theraphy> getTherapiesByPhysiotherapistId(Integer physiotherapistId){
        return theraphyRepository.findTheraphiesByPhysiotherapistId(physiotherapistId);
    }


    public List<Theraphy> getTherapiesByPatientId(Integer patientId) {
        return theraphyRepository.findTheraphiesByPatientId(patientId);
    }

//    public List<Appointment> getAppointmentsByTherapyId(Integer therapyId) {
//        List<Appointment> appointments = restTemplate.getForObject("http://localhost:7007/api/v1/appointments/getAppointmentsByTherapyId/" + therapyId, Appointment.class);
//        return appointments;
//    }
//

    @Override
    public Patient getPatientById(Integer patientId){
        Patient patient  = restTemplate.getForObject("http://localhost:8080/api/v1/patients/" + patientId, Patient.class);
        return patient;
    }

    @Override
    public Physiotherapist getPhysiotherapistById(Integer physiotherapistId){
        Physiotherapist physiotherapist  = restTemplate.getForObject("http://localhost:8080/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        return physiotherapist;
    }


}
