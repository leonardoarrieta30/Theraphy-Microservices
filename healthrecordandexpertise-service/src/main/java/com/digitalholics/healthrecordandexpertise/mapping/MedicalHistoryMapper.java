package com.digitalholics.healthrecordandexpertise.mapping;

import com.digitalholics.healthrecordandexpertise.domain.model.entity.MedicalHistory;
import com.digitalholics.healthrecordandexpertise.mapping.configuration.EnhancedModelMapper;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.CreateMedicalHistoryResource;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.MedicalHistoryResource;
import com.digitalholics.healthrecordandexpertise.resource.MedicalHistory.UpdateMedicalHistoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class MedicalHistoryMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public MedicalHistoryResource toResource(MedicalHistory model) {
        return mapper.map(model, MedicalHistoryResource.class);
    }

    public MedicalHistory toModel(CreateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }

    public MedicalHistory toModel(UpdateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }

    public Page<MedicalHistoryResource> modelListPage(List<MedicalHistory> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, MedicalHistoryResource.class), pageable, modelList.size());
    }
}
