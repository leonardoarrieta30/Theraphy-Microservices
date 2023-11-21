package com.digitalholics.patientservice.service;

import com.digitalholics.patientservice.domain.model.entity.Patient;
import com.digitalholics.patientservice.domain.model.entity.dto.Diagnosis;
import com.digitalholics.patientservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.patientservice.domain.model.entity.dto.Therapy;
import com.digitalholics.patientservice.domain.model.entity.dto.User;
import com.digitalholics.patientservice.domain.persistence.PatientRepository;
import com.digitalholics.patientservice.domain.service.PatientService;
import com.digitalholics.patientservice.feignsClients.DiagnosisFeignClient;
import com.digitalholics.patientservice.feignsClients.TherapyFeignClient;
import com.digitalholics.patientservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.patientservice.mapping.Exception.ResourceValidationException;
import com.digitalholics.patientservice.resource.CreatePatientResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String ENTITY = "Patient";

    private final PatientRepository patientRepository;

    private final Validator validator;

    private final DiagnosisFeignClient diagnosisFeignClient;
    private final TherapyFeignClient therapyFeignClient;

    private final RestTemplate restTemplate;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, Validator validator, DiagnosisFeignClient diagnosisFeignClient, TherapyFeignClient therapyFeignClient, RestTemplate restTemplate) {
        this.patientRepository = patientRepository;
        this.validator = validator;
        this.diagnosisFeignClient = diagnosisFeignClient;
        this.therapyFeignClient = therapyFeignClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getById(Integer patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public Patient create(CreatePatientResource patientResource) {
        Set<ConstraintViolation<CreatePatientResource>> violations = validator.validate(patientResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Patient patientWithDni =patientRepository.findPatientByDni(patientResource.getDni());

        if (patientWithDni != null)
            throw new ResourceValidationException(ENTITY,
                    "There is already a patient with the same DNI.");

        Patient patient = new Patient();
        patient.setPhotoUrl(patientResource.getPhotoUrl());
        patient.setBirthday(patientResource.getBirthday());
        patient.setLocation(patientResource.getLocation());
        patient.setDni(patientResource.getDni());


//        List<Physiotherapist> physiotherapists = this.getPhysiotherapists();
//        for (int i= 0; i< this.getPhysiotherapists().size(); i++){
//            if(physiotherapists.get(i).getUserId().equals(patientResource.getUserId())){
//                throw new ResourceNotFoundException("User equals");
//            }
//        }


       // Integer physiotherapistIdWithUser = this.getPhysiotherapist(patientResource.getUserId());

        //Patient patient1 = patientOptional.orElseThrow(() -> new NotFoundException("User exists or equals with id: " + patientResource.getUserId()));

//        if () {
//            throw new ResourceNotFoundException("User exists");
//        } else{
//            patient.setUserId(patientResource.getUserId());
//        }

//        if (patientResource.getUserId().equals(patientRepository.findPatientByUserId(patientResource.getUserId()).getUserId()) && this.getUserIdByPhysiotherapist(patientResource.getUserId())){
//            throw new ResourceNotFoundException("User equals or exists");
//        } else{
//            patient.setUserId(patientResource.getUserId());
//        }


        // Buscar al paciente por userId
        Patient existingPatient = patientRepository.findPatientByUserId(patientResource.getUserId());

// Validar si el userId existe en la lista de fisioterapeutas
        boolean isUserIdInPhysiotherapists = this.isExistsUserIdToPhysiotherapist(patientResource.getUserId());
        if (this.getUserById(patientResource.getUserId())) {
            if(existingPatient != null || isUserIdInPhysiotherapists){
                throw new ResourceNotFoundException("User equals or exists");
            }else{
                patient.setUserId(patientResource.getUserId());
            }
        }else {
            throw new ResourceNotFoundException("User don't found");
        }



        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Integer patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map(patient ->
                patientRepository.save(
                        patient.withPhotoUrl(request.getPhotoUrl())
                                .withBirthday(request.getBirthday())
                                .withLocation(request.getLocation())
                                .withDni(request.getDni())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public ResponseEntity<?> delete(Integer patientId) {
        return patientRepository.findById(patientId)
                .map(patient -> {
                    patientRepository.delete(patient);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }


    @Override
    public Therapy saveTherapy(Integer patientId, Therapy therapy){
        therapy.setPatientId(patientId);
        Therapy newTherapy = therapyFeignClient.save(therapy);
        return newTherapy;
    }

    @Override
    public List<Diagnosis> getDiagnosisByPatientId(Integer patientId){
        List<Diagnosis> diagnosisList = diagnosisFeignClient.getDiagnosisByPatientId(patientId);
        return diagnosisList;
    }



    @Override
    public Boolean getUserById(Integer userId){
        User user  = restTemplate.getForObject("http://gateway-service:8080/api/v1/users/" + userId, User.class);
        if(user!=null) return true;
        else return false;
    }

//
//    @Override
//    public List<Physiotherapist> getPhysiotherapists(){
//        List<Physiotherapist> physiotherapistList  = Collections.singletonList(restTemplate.getForObject("http://localhost:7008/api/v1/physiotherapists", Physiotherapist.class));
//        return physiotherapistList;
//    }


    @Override
    public Integer getUserId(Integer userId) {
        Optional<Patient> patient = this.patientRepository.findByUserId(userId);
        return patient.map(Patient::getUserId).orElse(null);
    }


    @Override
    public Boolean isExistsUserIdToPhysiotherapist(Integer userId){
        Integer userId2 = restTemplate.getForObject("http://gateway-service:8080/api/v1/physiotherapists/userId/" + userId , Integer.class);
        return userId2 != null;
    }






}
