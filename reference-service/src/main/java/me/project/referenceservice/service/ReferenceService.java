package me.project.referenceservice.service;

import me.project.referenceservice.dto.ReferenceRequest;
import me.project.referenceservice.dto.ReferenceResponse;

import java.util.List;

public interface ReferenceService {
    String createReference(ReferenceRequest request);
    void updateReference(ReferenceRequest request);
    void deleteReference(Long id);
    List<ReferenceResponse> listReference();
    ReferenceResponse getReferenceById(Long id);
}
