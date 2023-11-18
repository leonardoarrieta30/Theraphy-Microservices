package com.digitalholics.appointmentservice.service;


import com.digitalholics.appointmentservice.domain.model.entity.Appointment;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Patient;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.appointmentservice.domain.model.entity.dto.ResponseDTO;
import com.digitalholics.appointmentservice.domain.model.entity.dto.Theraphy2;
import com.digitalholics.appointmentservice.domain.persistence.AppointmentRepository;
import com.digitalholics.appointmentservice.domain.service.AppointmentService;
import com.digitalholics.appointmentservice.feignClients.TherapyFeignClient;
import com.digitalholics.appointmentservice.mapping.Exception.ResourceNotFoundException;
import com.digitalholics.appointmentservice.mapping.Exception.ResourceValidationException;
import com.digitalholics.appointmentservice.resource.CreateAppointmentResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final String ENTITY = "Appointment";
    private final AppointmentRepository appointmentRepository;

    private final Validator validator;

    private final TherapyFeignClient therapyFeignClient;


    private final RestTemplate restTemplate;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmenRepository,Validator validator,  TherapyFeignClient therapyFeignClient, RestTemplate restTemplate) {
        this.appointmentRepository = appointmenRepository;
        this.validator = validator;
        this.therapyFeignClient = therapyFeignClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Page<Appointment> getAll(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment getById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

//    @Override
//    public Appointment getAppointmentByTheraphyId(Integer theraphyId) {
//        return appointmentRepository.findAppointmentByTheraphyId(theraphyId);
//    }

     @Override
    public Appointment update(Integer appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentRepository.findById(appointmentId).map(appointment ->
                        appointmentRepository.save(
                                appointment.withTopic(request.getTopic())
                                        .withDiagnosis(request.getDiagnosis())
                                        .withDone(request.getDone())
                                        .withPlace(request.getPlace())
                                        .withHour(request.getHour())
                                        .withDate(request.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public Appointment create(CreateAppointmentResource appointmentResource) {
        Set<ConstraintViolation<CreateAppointmentResource>> violations = validator.validate(appointmentResource);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Appointment appointmentWithTopic = appointmentRepository.findByTopic(appointmentResource.getTopic());


        if(appointmentWithTopic != null)
            throw new ResourceValidationException(ENTITY,
                    "A Appointment with the same topic already exists.");

        Appointment appointment = new Appointment();
        appointment.setHour(appointmentResource.getHour());
        appointment.setDate(appointmentResource.getDate());
        appointment.setDone(appointmentResource.getDone());
        appointment.setPlace(appointmentResource.getPlace());
        appointment.setDiagnosis(appointmentResource.getDiagnosis());
        appointment.setTopic(appointmentResource.getTopic());


        if(this.getPhysiotherapist(appointmentResource.getPhysiotherapistId())){
            appointment.setPhysiotherapistId(appointmentResource.getPhysiotherapistId());
        }else{
            throw new ResourceNotFoundException("PhysiotherapistId not found");
        }

        if(this.getPatient(appointmentResource.getPatientId())){
            appointment.setPatientId(appointmentResource.getPatientId());
        }else{
            throw new ResourceNotFoundException("Patient not found");
        }

        return appointmentRepository.save(appointment);

    }

    @Override
    public ResponseEntity<?> delete(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).map( appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,appointmentId));
    }


//    public Theraphy2 saveTherapy(Integer appointmentId, Theraphy2 theraphy2){
//        theraphy2.setAppointmentId(appointmentId);
//        Theraphy2 theraphy2New = therapyFeignClient.save(theraphy2);
//        return theraphy2New;
//    }


    public Theraphy2 getTherapyByAppointmentId(Integer appointmentId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        if(appointment == null)
            throw new ResourceNotFoundException("Appointment not found");
        Theraphy2 theraphy2 = restTemplate.getForObject("http://localhost:8080/api/v1/theraphies/getTherapyByAppointment/" + appointmentId, Theraphy2.class);
        return theraphy2;
    }

//    public List<Appointment> getAppointmentsByTherapyId(Integer therapyId){
//        if(this.getTherapyById(therapyId)==null){
//            throw new ResourceNotFoundException("Therapy not found");
//        }
//        return appointmentRepository.findAppointmentsByTherapyId(therapyId);
//    }

    public Theraphy2 getTherapyById(Integer therapyId){
        Theraphy2 therapy = restTemplate.getForObject("http://localhost:8080/api/v1/theraphies/" + therapyId, Theraphy2.class);
        return therapy;
    }


    @Override
    public Boolean getPatient(Integer patientId){
        Patient patient = restTemplate.getForObject("http://localhost:8080/api/v1/patients/" + patientId ,  Patient.class);
        if(patient!= null) return true;
        else return false;
    }
    @Override
    public Boolean getPhysiotherapist(Integer physiotherapistId){
        Physiotherapist physiotherapist = restTemplate.getForObject("http://localhost:8080/api/v1/physiotherapists/" + physiotherapistId ,  Physiotherapist.class);
        if(physiotherapist!= null) return true;
        else return false;
    }







//    @Override
//    public Theraphy2 getTherapyById(Integer therapyId){
//        Theraphy2 theraphy2  = restTemplate.getForObject("http://localhost:7009/api/v1/theraphies/" + therapyId, Theraphy2.class);
//        return theraphy2;
//    }



}
