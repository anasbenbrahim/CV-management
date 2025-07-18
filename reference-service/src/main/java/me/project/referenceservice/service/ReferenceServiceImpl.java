package me.project.referenceservice.service;

import lombok.RequiredArgsConstructor;
import me.project.referenceservice.client.CvRestClient;
import me.project.referenceservice.dto.Cv;
import me.project.referenceservice.dto.ReferenceRequest;
import me.project.referenceservice.dto.ReferenceResponse;
import me.project.referenceservice.entity.Reference;
import me.project.referenceservice.mapper.ReferenceMapper;
import me.project.referenceservice.repository.ReferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {

    private final ReferenceRepository referenceRepository;
    private final ReferenceMapper referenceMapper;
    private final CvRestClient cvRestClient;

    @Override
    public String createReference(ReferenceRequest request) {
        cvRestClient.findCvById(request.cvId()); // Vérifie que le CV existe
        Reference reference = referenceMapper.toReference(request);
        reference = referenceRepository.save(reference);
        return reference.getId().toString();
    }

    @Override
    public void updateReference(ReferenceRequest request) {
        Reference existing = referenceRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Reference not found with ID: " + request.id()));
        cvRestClient.findCvById(request.cvId()); // Vérifie que le CV existe
        Reference updated = referenceMapper.toReference(request);
        updated.setId(existing.getId()); // Assure la mise à jour de l'entité existante
        referenceRepository.save(updated);
    }

    @Override
    public void deleteReference(Long id) {
        referenceRepository.deleteById(id);
    }

    @Override
    public List<ReferenceResponse> listReference() {
        return referenceRepository.findAll().stream().map(reference -> {
            Cv cv = cvRestClient.findCvById(reference.getCvId());
            ReferenceResponse response = referenceMapper.fromReference(reference);
            return new ReferenceResponse(
                    response.id(), response.fullName(), response.email(), response.phone(),
                    response.company(), response.position(), response.recommendation(),
                    response.cvId(), cv
            );
        }).collect(Collectors.toList());
    }

    @Override
    public ReferenceResponse getReferenceById(Long id) {
        Reference reference = referenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reference not found with ID: " + id));
        Cv cv = cvRestClient.findCvById(reference.getCvId());
        ReferenceResponse response = referenceMapper.fromReference(reference);
        return new ReferenceResponse(
                response.id(), response.fullName(), response.email(), response.phone(),
                response.company(), response.position(), response.recommendation(),
                response.cvId(), cv
        );
    }
}
