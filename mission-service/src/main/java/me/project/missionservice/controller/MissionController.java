package me.project.missionservice.controller;

import lombok.RequiredArgsConstructor;
import me.project.missionservice.dto.MissionRequest;
import me.project.missionservice.dto.MissionResponse;
import me.project.missionservice.service.MissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService service;

    @PostMapping
    public MissionResponse create(@RequestBody MissionRequest request) {
        return service.createMission(request);
    }

    @GetMapping("/{id}")
    public MissionResponse getById(@PathVariable Long id) {
        return service.getMissionById(id);
    }

    @GetMapping
    public List<MissionResponse> getAll() {
        return service.getAllMissions();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteMission(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionResponse> updateMission(
            @PathVariable Long id,
            @RequestBody MissionRequest request) {
        MissionResponse updatedMission = service.updateMission(id, request);
        return ResponseEntity.ok(updatedMission);
    }
}
