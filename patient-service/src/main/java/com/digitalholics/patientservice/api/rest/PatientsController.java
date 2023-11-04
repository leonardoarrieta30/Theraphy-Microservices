package com.digitalholics.patientservice.api.rest;

import com.digitalholics.patientservice.domain.model.entity.Patient;
import com.digitalholics.patientservice.domain.model.entity.dto.Appointment;
import com.digitalholics.patientservice.domain.model.entity.dto.ResponseDTO;
import com.digitalholics.patientservice.domain.model.entity.dto.Theraphy;
import com.digitalholics.patientservice.domain.service.PatientService;
import com.digitalholics.patientservice.mapping.PatientMapper;
import com.digitalholics.patientservice.resource.CreatePatientResource;
import com.digitalholics.patientservice.resource.PatientResource;
import com.digitalholics.patientservice.resource.UpdatePatientResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/patients", produces = "application/json")
@Tag(name = "Patients", description = "Create, read, update and delete patients")
public class PatientsController {

    private final PatientService patientService;
    private final PatientMapper mapper;

    @Autowired
    RestTemplate restTemplate;

    public PatientsController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @PostMapping("create-patient")
    public ResponseEntity<PatientResource> createPatient(@RequestBody CreatePatientResource resource) {
        return new ResponseEntity<>(mapper.toResource(patientService.create((resource))), HttpStatus.CREATED);
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Integer patientId) {
        return patientService.delete(patientId);
    }

    @GetMapping("allPatients")
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListPage(patientService.getAll(), pageable);
    }

    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Integer patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }

    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Integer patientId,
                                           @RequestBody UpdatePatientResource resource) {
        return mapper.toResource(patientService.update(patientId, mapper.toModel(resource)));
    }

    @GetMapping("patient/therapy/{therapyId}")
    public ResponseDTO getTherapyByAppointmentPatientById(@PathVariable Integer therapyId){
        ResponseDTO responseDTO = new ResponseDTO();
//        Patient patient;
//        patient = patientService.getById(patientId);
        ResponseEntity<Theraphy> responseEntity = restTemplate.getForEntity("http://localhost:7009/api/v1/theraphies/" + therapyId, Theraphy.class);

        Theraphy theraphy = responseEntity.getBody();

        responseDTO.setTheraphy(theraphy);
        //responseDTO.setAppointment(appointment);

        return responseDTO;
    }

}
