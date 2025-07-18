package me.project.cvservice.service;

import lombok.RequiredArgsConstructor;
import me.project.cvservice.Enum.Statut;
import me.project.cvservice.client.UserRestClient;
import me.project.cvservice.dto.CvRequest;
import me.project.cvservice.dto.CvResponse;
import me.project.cvservice.entity.Cv;
import me.project.cvservice.mapper.CvMapper;
import me.project.cvservice.repository.CvRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    private final CvRepository cvRepository;
    private final CvMapper cvMapper;
    private final UserRestClient userRestClient; // Injected UserRestClient

    @Override
    public String createCv(CvRequest request) {
        // Validate user existence
        userRestClient.findCustomerById(request.userId()); // Throws exception if user not found
        Cv cv = cvMapper.toCv(request);
        cv = cvRepository.save(cv);
        return cv.getId().toString();
    }

    @Override
    public void updateCv(CvRequest request) {
        Cv cv = cvRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot update CV:: No CV found with the provided ID: %s", request.id())
                ));
        // Validate user existence
        userRestClient.findCustomerById(request.userId());
        cvRepository.save(cvMapper.toCv(request));
    }

    @Override
    public void deleteCv(Long id) {
        cvRepository.deleteById(id);
    }

    @Override
    public List<CvResponse> listCv() {
        return cvRepository.findAll()
                .stream()
                .map(cv -> {
                    CvResponse response = cvMapper.fromCv(cv);
                    // Create new CvResponse with user details
                    return new CvResponse(
                            response.id(),
                            response.titre(),
                            response.resume(),
                            response.secteurActivite(),
                            response.niveauEtude(),
                            response.anneesExperience(),
                            response.localisation(),
                            response.langue(),
                            response.typeContratSouhaite(),
                            response.mobilite(),
                            response.pretentionSalariale(),
                            response.lienLinkedIn(),
                            response.lienPortfolio(),
                            response.statut(),
                            response.dateCreation(),
                            response.userId(),
                            userRestClient.findCustomerById(cv.getUserId())
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public CvResponse getCvById(Long id) {
        Cv cv = cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find CV:: No CV found with the provided ID: %s", id)
                ));
        CvResponse response = cvMapper.fromCv(cv);
        // Create new CvResponse with user details
        return new CvResponse(
                response.id(),
                response.titre(),
                response.resume(),
                response.secteurActivite(),
                response.niveauEtude(),
                response.anneesExperience(),
                response.localisation(),
                response.langue(),
                response.typeContratSouhaite(),
                response.mobilite(),
                response.pretentionSalariale(),
                response.lienLinkedIn(),
                response.lienPortfolio(),
                response.statut(),
                response.dateCreation(),
                response.userId(),
                userRestClient.findCustomerById(cv.getUserId())
        );
    }

    @Override
    public Cv achiveCv(Long id) {
        Cv cv = cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV not found with id: " + id));

        if (cv.getStatut() == Statut.ARCHIVED) {
            throw new IllegalStateException("CV is already archived.");
        }

        cv.setStatut(Statut.ARCHIVED);
        return cvRepository.save(cv);
    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }
}