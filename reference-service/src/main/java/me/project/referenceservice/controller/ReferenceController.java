package me.project.referenceservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.project.referenceservice.dto.ReferenceRequest;
import me.project.referenceservice.dto.ReferenceResponse;
import me.project.referenceservice.service.ReferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/references")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @PostMapping("/create")
    public ResponseEntity<String> createReference(@RequestBody @Valid ReferenceRequest request) {
        String id = referenceService.createReference(request);
        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<Void> updateReference(@RequestBody @Valid ReferenceRequest request) {
        referenceService.updateReference(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReference(@PathVariable Long id) {
        referenceService.deleteReference(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReferenceResponse>> listReferences() {
        return ResponseEntity.ok(referenceService.listReference());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenceResponse> getReferenceById(@PathVariable Long id) {
        return ResponseEntity.ok(referenceService.getReferenceById(id));
    }
}
