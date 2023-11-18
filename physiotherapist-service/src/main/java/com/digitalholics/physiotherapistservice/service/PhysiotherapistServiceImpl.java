package com.digitalholics.physiotherapistservice.service;

import com.digitalholics.physiotherapistservice.domain.model.dto.Patient;
import com.digitalholics.physiotherapistservice.domain.model.dto.Therapy;
import com.digitalholics.physiotherapistservice.domain.model.dto.User;
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
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;
import com.digitalholics.physiotherapistservice.domain.model.Physiotherapist;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhysiotherapistServiceImpl implements PhysiotherapistService {
    private static final String ENTITY = "Physiotherapist";

    private final PhysiotherapistRepository physiotherapistRepository;


    private final Validator validator;

    private final TherapyFeignClient therapyFeignClient;

    private final RestTemplate restTemplate;

    @Autowired
    public PhysiotherapistServiceImpl(PhysiotherapistRepository physiotherapistRepository, Validator validator, TherapyFeignClient therapyFeignClient, RestTemplate restTemplate) {
        this.physiotherapistRepository = physiotherapistRepository;
        this.validator = validator;
        this.therapyFeignClient = therapyFeignClient;
        this.restTemplate = restTemplate;
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
        physiotherapist.setSpecialization(physiotherapistResource.getSpecialization());
        physiotherapist.setYearsExperience(physiotherapistResource.getYearsExperience());
        physiotherapist.setRating(0.0);
        physiotherapist.setFees(physiotherapistResource.getFees());

//        List<Patient> patients = this.getPatients();
//        for (int i= 0; i< this.getPatients().size(); i++){
//            if(patients.get(i).getUserId().equals(physiotherapistResource.getUserId())){
//                throw new ResourceNotFoundException("User equals");
//            }
//        }

//
//        Optional<Physiotherapist> physiotherapistOptional = physiotherapistRepository.findByUserId(physiotherapistResource.getUserId());
//
//        Physiotherapist physiotherapist1 = physiotherapistOptional.orElseThrow(() -> new NotFoundException("User exists or equals with id: " + physiotherapistResource.getUserId()));
//
//        if (this.getUserById(physiotherapistResource.getUserId()) == null) {
//            throw new ResourceNotFoundException("User not found");
//        } else if (physiotherapist1.getUserId().equals(physiotherapistResource.getUserId())) {
//            throw new ResourceNotFoundException("User exists");
//        } else{
//            physiotherapist.setUserId(physiotherapistResource.getUserId());
//        }

//
//        if(this.getUserById(physiotherapistResource.getUserId()) == null){
//            throw new ResourceNotFoundException("User not found");
//        }else{
//            physiotherapist.setUserId(physiotherapistResource.getUserId());
//        }

        Physiotherapist existingPatient = physiotherapistRepository.findPhysiotherapistByUserId(physiotherapistResource.getUserId());

// Validar si el userId existe en la lista de fisioterapeutas
        boolean isUserIdInPatients = this.isExistsUserIdToPatient(physiotherapistResource.getUserId());
        if (this.getUserById(physiotherapistResource.getUserId())) {
            if(existingPatient != null || isUserIdInPatients){
                throw new ResourceNotFoundException("User equals or exists");
            }else{
                physiotherapist.setUserId(physiotherapistResource.getUserId());
            }
        }else {
            throw new ResourceNotFoundException("User don't found");
        }

        return physiotherapistRepository.save(physiotherapist);

    }

    @Override
    public Physiotherapist update(Integer physiotherapistId, UpdatePhysiotherapistResource request) {
        Physiotherapist physiotherapist = getById(physiotherapistId);

        physiotherapist.setDni(request.getDni() != null ? request.getDni() : physiotherapist.getDni());
        physiotherapist.setAge(request.getAge() != null ? request.getAge() : physiotherapist.getAge());
        physiotherapist.setPhotoUrl(request.getPhotoUrl() != null ? request.getPhotoUrl() : physiotherapist.getPhotoUrl());
        physiotherapist.setLocation(request.getLocation() != null ? request.getLocation() : physiotherapist.getLocation());
        physiotherapist.setBirthdayDate(request.getBirthdayDate() != null ? request.getBirthdayDate() : physiotherapist.getBirthdayDate());
        physiotherapist.setRating(request.getRating() != null ? request.getRating() : physiotherapist.getRating());
        physiotherapist.setSpecialization(request.getSpecialization() != null ? request.getSpecialization() : physiotherapist.getSpecialization());
        physiotherapist.setYearsExperience(request.getYearsExperience() != null ? request.getYearsExperience() : physiotherapist.getYearsExperience());
        physiotherapist.setFees(request.getFees() != null ? request.getFees() : physiotherapist.getFees());

        return physiotherapistRepository.save(physiotherapist);
    }

    @Override
    public ResponseEntity<?> delete(Integer physiotherapistId) {
        return physiotherapistRepository.findById(physiotherapistId).map(physiotherapist -> {
            physiotherapistRepository.delete(physiotherapist);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,physiotherapistId));    }


    //ni lo uso
    @Override
    public Therapy saveTherapyToPatientAndPhysiotherapist(Integer physiotherapistId,Integer patientId, Therapy therapy){
        therapy.setPhysiotherapistId(physiotherapistId);
        therapy.setPatientId(patientId);
        Therapy newTherapy = therapyFeignClient.save(therapy);
        return newTherapy;
    }

    @Override
    public Therapy getTherapyByPhysiotherapistId(Integer physiotherapistId){
        Physiotherapist physiotherapist = physiotherapistRepository.findById(physiotherapistId).orElseThrow(()-> new ResourceNotFoundException(ENTITY,physiotherapistId));

        return therapyFeignClient.getTherapy(physiotherapistId);
    }

    @Override
    public Patient getPatientById(Integer patientId){
        Patient patient  = restTemplate.getForObject("http://localhost:8080/api/v1/patients/" + patientId, Patient.class);
        return patient;
    }


    @Override
    public Boolean getUserById(Integer userId){
        User user  = restTemplate.getForObject("http://localhost:8080/api/v1/users/" + userId, User.class);
        if(user!=null) return true;
        else return false;
    }


    @Override
    public List<Patient> getPatients(){
        List<Patient> patientList  = Collections.singletonList(restTemplate.getForObject("http://localhost:8080/api/v1/patients/allPatients", Patient.class));
        return patientList;
    }

    @Override
    public Integer getPatient(Integer patientId){
        Patient patient = restTemplate.getForObject("http://localhost:8080/api/v1/patients/" + patientId ,  Patient.class);
        return patient.getUserId();
    }

    @Override
    public Integer getUserId(Integer userId) {
        Optional<Physiotherapist> physiotherapist = this.physiotherapistRepository.findByUserId(userId);
        return physiotherapist.map(Physiotherapist::getUserId).orElse(null);
    }

    @Override
    public Boolean isExistsUserIdToPatient(Integer userId){
        Integer userId2 = restTemplate.getForObject("http://localhost:8080/api/v1/patients/userId/" + userId , Integer.class);
        return userId2 != null;
    }



}
