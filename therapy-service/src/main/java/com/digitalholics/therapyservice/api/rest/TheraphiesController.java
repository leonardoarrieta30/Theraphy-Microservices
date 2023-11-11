package com.digitalholics.therapyservice.api.rest;

import com.digitalholics.therapyservice.domain.model.entity.Theraphy;
import com.digitalholics.therapyservice.domain.service.TheraphyService;
import com.digitalholics.therapyservice.mapping.TheraphyMapper;
import com.digitalholics.therapyservice.resource.CreateTheraphyResource;
import com.digitalholics.therapyservice.resource.TheraphyResource;
import com.digitalholics.therapyservice.resource.UpdateTheraphyResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/theraphies", produces = "application/json")
@Tag(name = "Theraphies", description = "Create, read, update and delete theraphies")
public class TheraphiesController {

    private final TheraphyService theraphyService;

    private final TheraphyMapper mapper;

    public TheraphiesController(TheraphyService theraphyService, TheraphyMapper mapper) {
        this.theraphyService = theraphyService;
        this.mapper = mapper;
    }

    @PostMapping("create-theraphy")
    public ResponseEntity<TheraphyResource> createTheraphy(@RequestBody CreateTheraphyResource resource) {
        return new ResponseEntity<>(mapper.toResource(theraphyService.create((resource))), HttpStatus.CREATED);
    }

    @DeleteMapping("{theraphyId}")
    public ResponseEntity<?> deleteTheraphy(@PathVariable Integer theraphyId) {
        return theraphyService.delete(theraphyId);
    }

    @GetMapping("allTheraphies")
    public Page<TheraphyResource> getAllTheraphies(Pageable pageable) {
        return mapper.modelListPage(theraphyService.getAll(), pageable);
    }

    @GetMapping("{theraphyId}")
    public TheraphyResource getTheraphyById(@PathVariable Integer theraphyId) {
        return mapper.toResource(theraphyService.getById(theraphyId));
    }

    @PutMapping("{theraphyId}")
    public TheraphyResource updateTheraphy(@PathVariable Integer theraphyId,
                                                 @RequestBody UpdateTheraphyResource resource) {
        return mapper.toResource(theraphyService.update(theraphyId, mapper.toModel(resource)));
    }

//    @GetMapping("/byAppointment/{appointmentId}")
//    public ResponseEntity<Theraphy> getByTherapyAppointmentId(@PathVariable("appointmentId") Integer appointmentId) {
//        Theraphy theraphy = theraphyService.byAppointmentId(appointmentId);
//        return ResponseEntity.ok(theraphy);
//    }

    //query did not return a unique result: 2
//    @GetMapping("/getTherapyByPhysiotherapist/{physiotherapistId}")
//    public TheraphyResource getTherapyByPhysiotherapistId(@PathVariable("physiotherapistId") Integer PhysiotherapistId){
//        Theraphy therapy = theraphyService.getTherapyByPhysiotherapistId(PhysiotherapistId);
//        return mapper.toResource(therapy);
//    }
    @GetMapping("/getTherapiesByPhysiotherapistId/{physiotherapistId}")
    public List<Theraphy> getTherapiesByPhysiotherapistId(@PathVariable("physiotherapistId") Integer PhysiotherapistId){
        List<Theraphy> listTherapy = theraphyService.getTherapiesByPhysiotherapistId(PhysiotherapistId);
        return listTherapy;
    }




    @GetMapping("/getTherapiesByPatientId/{patientId}")
    public List<Theraphy> getTherapyByPatientId(@PathVariable("patientId") Integer patientId){
        List<Theraphy> listTherapy = theraphyService.getTherapiesByPatientId(patientId);
        return listTherapy;
    }


}
