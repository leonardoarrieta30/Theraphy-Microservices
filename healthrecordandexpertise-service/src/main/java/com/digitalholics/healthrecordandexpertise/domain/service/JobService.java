package com.digitalholics.healthrecordandexpertise.domain.service;


import com.digitalholics.healthrecordandexpertise.domain.model.entity.Job;
import com.digitalholics.healthrecordandexpertise.domain.model.entity.dto.Physiotherapist;
import com.digitalholics.healthrecordandexpertise.resource.CreateJobResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    List<Job> getAll();
    Page<Job> getAll(Pageable pageable);
    Job getById(Integer jobId);
    Job create(CreateJobResource job);
    Job update(Integer jobId, Job request);
    ResponseEntity<?> delete(Integer jobId);
    Physiotherapist getPhysiotherapistById(Integer physiotherapistId);
}
