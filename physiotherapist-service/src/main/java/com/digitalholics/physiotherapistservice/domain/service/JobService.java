package com.digitalholics.physiotherapistservice.domain.service;


import com.digitalholics.physiotherapistservice.domain.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    List<Job> getAll();
    Page<Job> getAll(Pageable pageable);
    Job getById(Integer jobId);
    Job create(Job job);
    Job update(Integer jobId, Job request);
    ResponseEntity<?> delete(Integer jobId);
}
