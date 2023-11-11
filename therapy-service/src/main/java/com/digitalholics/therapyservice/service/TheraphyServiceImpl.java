package com.digitalholics.therapyservice.service;


import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.domain.persistence.TheraphyRepository;
import com.digitalholics.therapyservice.domain.service.TheraphyService;
import com.digitalholics.therapyservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.therapyservice.mapping.Exception.ResourceValidationException;
import com.digitalholics.therapyservice.resource.CreateTheraphyResource;
import com.digitalholics.therapyservice.resource.TheraphyResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TheraphyServiceImpl implements TheraphyService {


    private static final String ENTITY = "Theraphy";

    private final TheraphyRepository theraphyRepository;





    private final Validator validator;

    
    public TheraphyServiceImpl(TheraphyRepository theraphyRepository, Validator validator) {
        this.theraphyRepository = theraphyRepository;
        this.validator = validator;
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
        theraphy.setPhysiotherapistId(theraphyResource.getPhysiotherapistId());
        theraphy.setPatientId(theraphyResource.getPatientId());


       return theraphyRepository.save(theraphy);
    }

    @Override
    public Theraphy update(Integer theraphyId, Theraphy request) {
        Set<ConstraintViolation<Theraphy>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return theraphyRepository.findById(theraphyId).map(theraphy ->
                theraphyRepository.save(
                        theraphy.withTheraphyName(request.getTheraphyName())
                                .withAppointmentGap(request.getAppointmentGap())
                                .withStartAt(request.getStartAt())
                                .withFinishAt(request.getFinishAt())
                                .withAppointmentQuantity(request.getAppointmentQuantity())
                )).orElseThrow(()-> new ResourceNotFoundException(ENTITY, theraphyId));


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
//    public Theraphy byPatientId(Integer patientId) {
//        return theraphyRepository.findByPatientId(patientId);
//    }




}
