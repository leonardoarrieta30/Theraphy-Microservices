package com.digitalholics.healthrecordandexpertise.mapping;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.Diagnosis;
import com.digitalholics.healthrecordandexpertise.mapping.configuration.EnhancedModelMapper;
import com.digitalholics.healthrecordandexpertise.resource.DiagnosisResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DiagnosisMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public DiagnosisResource toResource(Diagnosis model) {
        return mapper.map(model, DiagnosisResource.class);
    }


    public Page<DiagnosisResource> modelListPage(List<Diagnosis> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DiagnosisResource.class), pageable, modelList.size());
    }
}
