package com.digitalholics.therapyservice.api.rest;

import com.digitalholics.therapyservice.domain.service.TreatmentService;
import com.digitalholics.therapyservice.mapping.TreatmentMapper;
import com.digitalholics.therapyservice.resource.Treatment.CreateTreatmentResource;
import com.digitalholics.therapyservice.resource.Treatment.TreatmentResource;
import com.digitalholics.therapyservice.resource.Treatment.UpdateTreatmentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/treatments", produces = "application/json")
@Tag(name = "Treatments", description = "Create, read, update and delete treatments")
public class TreatmentsController {
    private final TreatmentService treatmentService;

    private final TreatmentMapper mapper;

    public TreatmentsController(TreatmentService treatmentService, TreatmentMapper mapper) {
        this.treatmentService = treatmentService;
        this.mapper = mapper;
    }

    @PostMapping("create-treatment")
    public ResponseEntity<TreatmentResource> createTreatment(@RequestBody CreateTreatmentResource resource) {
        return new ResponseEntity<>(mapper.toResource(treatmentService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{treatmentId}")
    public TreatmentResource updateTreatment(@PathVariable Integer treatmentId,
                                             @RequestBody UpdateTreatmentResource resource) {
        return mapper.toResource(treatmentService.update(treatmentId, mapper.toModel(resource)));
    }
    
    @GetMapping("allTreatment")
    public Page<TreatmentResource> getAllTreatments(Pageable pageable) {
        return mapper.modelListPage(treatmentService.getAll(), pageable);
    }

    @GetMapping("{treatmentId}")
    public TreatmentResource getTreatmentById(@PathVariable Integer treatmentId) {
        return mapper.toResource(treatmentService.getById(treatmentId));
    }

//    @GetMapping("treatment/{theraphyId}")
//    public Page<TreatmentResource> getTreatmentByTheraphyId(@PathVariable Integer theraphyId, Pageable pageable){
//        return mapper.modelListPage(treatmentService.getTreatmentByTheraphyId(theraphyId), pageable);
//    }

    @DeleteMapping("{treatmentId}")
    public ResponseEntity<?> deleteTreatment(@PathVariable Integer treatmentId) {
        return treatmentService.delete(treatmentId);
    }



}
