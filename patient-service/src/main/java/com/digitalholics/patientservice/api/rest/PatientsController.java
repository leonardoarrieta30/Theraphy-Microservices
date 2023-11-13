package com.digitalholics.patientservice.api.rest;

import com.digitalholics.patientservice.domain.model.entity.dto.Appointment;
import com.digitalholics.patientservice.domain.model.entity.dto.Diagnosis;
import com.digitalholics.patientservice.domain.model.entity.dto.ResponseDTO;
import com.digitalholics.patientservice.domain.model.entity.dto.Therapy;
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

import java.util.List;

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

    @RequestMapping(value = "getTheraphyById/{theraphyId}", method = RequestMethod.GET)
    public Therapy getTheraphyById(@PathVariable Integer theraphyId){
        Therapy therapy = restTemplate.getForObject("http://localhost:7009/api/v1/theraphies/" + theraphyId, Therapy.class);
        return therapy;
    }

    @RequestMapping(value = "getAppointmentById/{appointmentId}", method = RequestMethod.GET)
    public Appointment getAppointmentById(@PathVariable Integer appointmentId){
        Appointment appointment = restTemplate.getForObject("http://localhost:7007/api/v1/appointments/" + appointmentId, Appointment.class);
        return appointment;
    }

    @GetMapping("patient/therapy/{therapyId}")
    public ResponseDTO getTherapyByAppointmentPatientById(@PathVariable Integer therapyId){
        ResponseDTO responseDTO = new ResponseDTO();
//        Patient patient;
//        patient = patientService.getById(patientId);
        ResponseEntity<Therapy> responseEntity = restTemplate.getForEntity("http://localhost:7009/api/v1/theraphies/" + therapyId, Therapy.class);

        Therapy therapy = responseEntity.getBody();

        responseDTO.setTherapy(therapy);
        //responseDTO.setAppointment(appointment);

        return responseDTO;
    }


//normalmente el physi crea la terapia, pero nose porque cree esto xD
//    @PostMapping("/saveTherapy/{patientId}")
//    public ResponseEntity<Therapy> saveTherapy(@PathVariable("patientId") Integer patientId, @RequestBody Therapy therapy){
//        if(patientService.getById(patientId) == null){
//            return ResponseEntity.notFound().build();
//        }
//        Therapy newTherapy = patientService.saveTherapy(patientId, therapy);
//        return ResponseEntity.ok(therapy);
//    }

    @GetMapping("/getDiagnosisByPatientId/{patientId}")
    public ResponseEntity<List<Diagnosis>> getDiagnosisByPatientId(@PathVariable("patientId") Integer patientId){
        if(patientService.getById(patientId) == null){
            return ResponseEntity.notFound().build();
        }
        List<Diagnosis> diagnosisList = patientService.getDiagnosisByPatientId(patientId);
        return ResponseEntity.ok(diagnosisList);

    }

    @GetMapping("userId/{userId}")
    //@PreAuthorize("hasAuthority('patient:read')")
    public Integer getPatientByUserId(@PathVariable Integer userId) {
        return patientService.getUserId(userId);
    }


}
