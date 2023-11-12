package com.digitalholics.appointmentservice.api.rest;


import com.digitalholics.appointmentservice.domain.model.entity.Appointment;
import com.digitalholics.appointmentservice.domain.model.entity.Theraphy;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.appointmentservice.domain.model.entity.dto.ResponseDTO;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Theraphy2;
import com.digitalholics.appointmentservice.domain.service.AppointmentService;
import com.digitalholics.appointmentservice.mapping.AppointmentMapper;
import com.digitalholics.appointmentservice.resource.AppointmentResource;
import com.digitalholics.appointmentservice.resource.CreateAppointmentResource;
import com.digitalholics.appointmentservice.resource.UpdateAppointmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = "application/json")
@Tag(name = "Appointments", description = "Create, read, update and delete appointments")
public class AppointmentsController {
    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;

    @Autowired
    RestTemplate restTemplate;


    public AppointmentsController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @GetMapping("allAppointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAll(), pageable);
    }

    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Integer appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

//    @GetMapping("appointment/{theraphyId}")
//    public AppointmentResource getAppointmentByTheraphyId(@PathVariable Integer theraphyId) {
//        return mapper.toResource(appointmentService.getAppointmentByTheraphyId(theraphyId));
//    }

    @PostMapping()
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(appointmentService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Integer appointmentId,
                                                 @RequestBody UpdateAppointmentResource resource) {
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(resource)));
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer appointmentId) {
        return appointmentService.delete(appointmentId);
    }

    @GetMapping("/therapy/{appointmentId}")
    public ResponseEntity<Theraphy2> getTherapyByAppointmentId(@PathVariable("appointmentId") Integer appointmentId){
        Appointment appointment = appointmentService.getById(appointmentId);
        if(appointment == null)
            return ResponseEntity.notFound().build();

        Theraphy2 therapy = appointmentService.getTherapyByAppointmentId(appointmentId);
        return ResponseEntity.ok(therapy);
    }

//
//    @GetMapping("appointment/theraphy-patient/{patientId}")
//    public Page<AppointmentResource> getAppointmentsByTheraphyByPatientId(@PathVariable Integer patientId, Pageable pageable) {
//        return mapper.modelListPage(appointmentService.getAppointmentsByTheraphyByPatientId(patientId),pageable);
//    }
//
//    @GetMapping("appointment/theraphy-physiotherapist/{physiotherapistId}")
//    public Page<AppointmentResource> getAppointmentsByTheraphyByPhysiotherapistId(@PathVariable Integer physiotherapistId, Pageable pageable) {
//        return mapper.modelListPage(appointmentService.getAppointmentsByTheraphyByPhysiotherapistId(physiotherapistId), pageable);
//    }

//    @RequestMapping(value = "getAppointmentByPhysiotherapistId/{physiotherapistId}", method = RequestMethod.GET)
//    public Appointment getAppointmentByPhysiotherapist(@PathVariable Integer physiotherapistId){
//        Physiotherapist physiotherapist = this.getPhysiotherapistById(physiotherapistId);
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<Physiotherapist> entity = new HttpEntity<>(physiotherapist, headers);
////
////        ResponseEntity<String> updateResponse = restTemplate.exchange(
////                "http://localhost:7008/api/v1/physiotherapists/" + physiotherapistId,
////                HttpMethod.GET,
////                entity,
////                String.class
////        );
//        //restTemplate.getForObject("http://localhost:7007/api/v1/appointments/" + 1, Appointment.class);
//        return physiotherapist.getAppointment();
//    }




//    @RequestMapping(value = "getPhysiotherapistById/{physiotherapistId}", method = RequestMethod.GET)
//     public String getPhysiotherapistById(@PathVariable Integer physiotherapistId){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return restTemplate.exchange("http://localhost:7008/api/v1/physiotherapists/"  + physiotherapistId, HttpMethod.GET, entity, String.class).getBody();
//    }

//
//    @RequestMapping(value = "getPhysiotherapistById/{physiotherapistId}", method = RequestMethod.GET)
//    public Physiotherapist getPhysiotherapistById(@PathVariable Integer physiotherapistId){
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<>(headers);
////        return restTemplate.exchange("http://localhost:7008/api/v1/physiotherapists/"  + physiotherapistId, HttpMethod.GET, entity, String.class).getBody();
////
//        Physiotherapist physiotherapist = restTemplate.getForObject("http://localhost:7008/api/v1/physiotherapists/"  + physiotherapistId, Physiotherapist.class);
//        return physiotherapist;
//    }


//    @RequestMapping(value = "getPhysiotherapistById/{physiotherapistId}", method = RequestMethod.GET)
//    public Appointment getAppointment(@PathVariable Integer physiotherapistId){
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<>(headers);
////        return restTemplate.exchange("http://localhost:7008/api/v1/physiotherapists/"  + physiotherapistId, HttpMethod.GET, entity, String.class).getBody();
////
//        Appointment appointment = restTemplate.getForObject("http://localhost:7007/api/v1/appointments/"  + physiotherapistId, Appointment.class);
//        return appointment;
//    }


//    @RequestMapping(value = "getPhysiotherapistById/{physiotherapistId}", method = RequestMethod.GET)
//    public Theraphy getAppointment(@PathVariable Integer physiotherapistId){
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<String> entity = new HttpEntity<>(headers);
////        return restTemplate.exchange("http://localhost:7008/api/v1/physiotherapists/"  + physiotherapistId, HttpMethod.GET, entity, String.class).getBody();
////
//        return
//
//        Theraphy theraphy = restTemplate.getForObject("http://localhost:7007/api/v1/appointments/"  + physiotherapistId, Theraphy.class);
//        return theraphy;
//    }

    @GetMapping("/therapy/{physiotherapistId}/{therapyId}")
    public ResponseDTO getTherapyPhysiotherapistId(@PathVariable Integer physiotherapistId, @PathVariable Integer therapyId){

        //Integer  appointmentId = getAppointmentByTheraphyId(1).getId();
        ResponseDTO responseDTO = new ResponseDTO();
        Appointment appointment;
        appointment = appointmentService.getById(physiotherapistId);
        ResponseEntity<Physiotherapist> responseEntity = restTemplate.getForEntity("http://localhost:7008/api/v1/physiotherapists/" + physiotherapistId, Physiotherapist.class);
        ResponseEntity<Theraphy2> responseEntity1 = restTemplate.getForEntity("http://localhost:7009/api/v1/theraphies/" + therapyId, Theraphy2.class);

        Physiotherapist physiotherapist = responseEntity.getBody();
        Theraphy2 theraphy2 = responseEntity1.getBody();

        //responseDTO.setAppointment(appointment);
        responseDTO.setPhysiotherapist(physiotherapist);
        //responseDTO.setTreatment(appointment);
        responseDTO.setTheraphy2(theraphy2);

        return responseDTO;
    }


    @GetMapping("getAppointmentsByTherapyId/{therapyId}")
    public List<Appointment> getAppointmentsByTherapyId(@PathVariable("therapyId") Integer therapyId){
        return appointmentService.getAppointmentsByTherapyId(therapyId);
    }



//    @PostMapping("/saveTherapy/{appointmentId}")
//    public ResponseEntity<Theraphy2> saveTherapy(@PathVariable("appointmentId") Integer appointmentId, @RequestBody Theraphy2 theraphy2){
//        Theraphy2 theraphy2New = appointmentService.saveTherapy(appointmentId,theraphy2);
//        return ResponseEntity.ok(theraphy2);
//    }





}
