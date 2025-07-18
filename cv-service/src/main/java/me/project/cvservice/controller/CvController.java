package me.project.cvservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.project.cvservice.dto.CvRequest;
import me.project.cvservice.dto.CvResponse;
import me.project.cvservice.entity.Cv;
import me.project.cvservice.mapper.CvMapper;
import me.project.cvservice.service.CvService;
import me.project.cvservice.service.CvServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
@RequiredArgsConstructor
public class CvController {

    private final CvService service;
    private final CvMapper cvMapper;
    private final CvServiceImpl cvServiceImpl;

    @PostMapping
    public ResponseEntity<String> createCv(
            @RequestBody @Valid CvRequest request
    ) {
        return ResponseEntity.ok(this.service.createCv(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCv(
            @RequestBody @Valid CvRequest request
    ) {
        this.service.updateCv(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CvResponse>> findAll() {
        return ResponseEntity.ok(this.service.listCv());
    }

    @GetMapping("/{cv-id}")
    public ResponseEntity<CvResponse> findById(
            @PathVariable("cv-id") Long cvId
    ) {
        return ResponseEntity.ok(this.service.getCvById(cvId));
    }

    @DeleteMapping("/{cv-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("cv-id") Long cvId
    ) {
        this.service.deleteCv(cvId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/archive/{cv-id}")
    public ResponseEntity<CvResponse> archiveCv(
            @PathVariable("cv-id") Long cvId
    ) {
        Cv cv = this.service.achiveCv(cvId);
        CvResponse response = this.cvMapper.fromCv(cv);
        // Add user details
        return ResponseEntity.ok(new CvResponse(
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
                this.cvServiceImpl.getUserRestClient().findCustomerById(cv.getUserId())
        ));
    }
}