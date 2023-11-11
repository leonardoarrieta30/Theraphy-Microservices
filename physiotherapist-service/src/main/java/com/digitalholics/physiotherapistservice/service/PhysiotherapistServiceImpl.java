package com.digitalholics.physiotherapistservice.service;

import com.digitalholics.physiotherapistservice.domain.model.dto.Therapy;
import com.digitalholics.physiotherapistservice.domain.persistence.PhysiotherapistRepository;
import com.digitalholics.physiotherapistservice.domain.service.PhysiotherapistService;
import com.digitalholics.physiotherapistservice.feignClients.TherapyFeignClient;
import com.digitalholics.physiotherapistservice.mapping.exception.ResourceNotFoundException;
import com.digitalholics.physiotherapistservice.mapping.exception.ResourceValidationException;
import com.digitalholics.physiotherapistservice.resources.CreatePhysiotherapistResource;
import com.digitalholics.physiotherapistservice.resources.UpdatePhysiotherapistResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService {
    private static final String ENTITY = "Physiotherapist";

    private final PhysiotherapistRepository physiotherapistRepository;


    private final Validator validator;

    private final TherapyFeignClient therapyFeignClient;

    @Autowired
    public PhysiotherapistServiceImpl(PhysiotherapistRepository physiotherapistRepository, Validator validator, TherapyFeignClient therapyFeignClient) {
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
        this.therapyFeignClient = therapyFeignClient;
    }

    @Override
    public List<Physiotherapist> getAll() {
        return physiotherapistRepository.findAll();
    }

    @Override
    public Page<Physiotherapist> getAll(Pageable pageable) {
        return physiotherapistRepository.findAll(pageable);
    }

    @Override
    public Physiotherapist getById(Integer physiotherapistId) {
        return physiotherapistRepository.findById(physiotherapistId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, physiotherapistId));    }

    @Override
    public Physiotherapist create(CreatePhysiotherapistResource physiotherapistResource) {
        Set<ConstraintViolation<CreatePhysiotherapistResource>> violations = validator.validate(physiotherapistResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        Physiotherapist physiotherapistWithDni = physiotherapistRepository.findPhysiotherapistByDni(physiotherapistResource.getDni());

        if(physiotherapistWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "A physiotherapist with the same Dni first name already exists.");

        Physiotherapist physiotherapist = new Physiotherapist();
        physiotherapist.setAge(physiotherapistResource.getAge());
        physiotherapist.setDni(physiotherapistResource.getDni());
        physiotherapist.setLocation(physiotherapistResource.getLocation());
        physiotherapist.setBirthdayDate(physiotherapistResource.getBirthdayDate());
        physiotherapist.setPhotoUrl(physiotherapistResource.getPhotoUrl());
        physiotherapist.setConsultationQuantity(0);
        physiotherapist.setSpecialization(physiotherapistResource.getSpecialization());
        physiotherapist.setYearsExperience(physiotherapistResource.getYearsExperience());
        physiotherapist.setRating(0.0);
        physiotherapist.setPatientQuantity(0);
        physiotherapist.setFees(physiotherapistResource.getFees());

        return physiotherapistRepository.save(physiotherapist);    }

    @Override
    public Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request) {
        Physiotherapist physiotherapist = getById(physiotherapistId);

        physiotherapist.setDni(request.getDni() != null ? request.getDni() : physiotherapist.getDni());
        physiotherapist.setAge(request.getAge() != null ? request.getAge() : physiotherapist.getAge());
        physiotherapist.setPhotoUrl(request.getPhotoUrl() != null ? request.getPhotoUrl() : physiotherapist.getPhotoUrl());
        physiotherapist.setPatientQuantity(request.getPatientQuantity() != null ? request.getPatientQuantity() : physiotherapist.getPatientQuantity());
        physiotherapist.setLocation(request.getLocation() != null ? request.getLocation() : physiotherapist.getLocation());
        physiotherapist.setBirthdayDate(request.getBirthdayDate() != null ? request.getBirthdayDate() : physiotherapist.getBirthdayDate());
        physiotherapist.setRating(request.getRating() != null ? request.getRating() : physiotherapist.getRating());
        physiotherapist.setSpecialization(request.getSpecialization() != null ? request.getSpecialization() : physiotherapist.getSpecialization());
        physiotherapist.setYearsExperience(request.getYearsExperience() != null ? request.getYearsExperience() : physiotherapist.getYearsExperience());
        physiotherapist.setConsultationQuantity(request.getConsultationQuantity() != null ? request.getConsultationQuantity() : physiotherapist.getConsultationQuantity());
        physiotherapist.setFees(request.getFees() != null ? request.getFees() : physiotherapist.getFees());

        return physiotherapistRepository.save(physiotherapist);
    }

    @Override
    public ResponseEntity<?> delete(Integer physiotherapistId) {
        return physiotherapistRepository.findById(physiotherapistId).map(physiotherapist -> {
            physiotherapistRepository.delete(physiotherapist);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,physiotherapistId));    }


    public Therapy saveTherapyToPatientAndPhysiotherapist(Integer physiotherapistId,Integer patientId, Therapy therapy){
        therapy.setPhysiotherapistId(physiotherapistId);
        therapy.setPatientId(patientId);
        Therapy newTherapy = therapyFeignClient.save(therapy);
        return newTherapy;
    }


    public Therapy getTherapyByPhysiotherapistId(Integer physiotherapistId){
        Physiotherapist physiotherapist = physiotherapistRepository.findById(physiotherapistId).orElseThrow(()-> new ResourceNotFoundException(ENTITY,physiotherapistId));

        return therapyFeignClient.getTherapy(physiotherapistId);
    }



}
