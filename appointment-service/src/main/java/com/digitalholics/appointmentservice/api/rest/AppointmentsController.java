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
    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Integer appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @GetMapping("allAppointments")
    public Page<AppointmentResource> getAllAppointments(Pageable pageable) {
        return mapper.modelListPage(appointmentService.getAll(), pageable);
    }

    @GetMapping("appointment/{theraphyId}")
    public AppointmentResource getAppointmentByTheraphyId(@PathVariable Integer theraphyId) {
        return mapper.toResource(appointmentService.getAppointmentByTheraphyId(theraphyId));
    }
    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Integer appointmentId,
                                                 @RequestBody UpdateAppointmentResource resource) {
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(resource)));
    }
    @PostMapping("create_appointment")
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(appointmentService.create((resource))), HttpStatus.CREATED);
    }
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer appointmentId) {
        return appointmentService.delete(appointmentId);
    }



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





}
