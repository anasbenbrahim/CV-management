package me.project.missionservice.service;

import me.project.missionservice.dto.MissionRequest;
import me.project.missionservice.dto.MissionResponse;

import java.util.List;

public interface MissionService {
    MissionResponse createMission(MissionRequest request);
    MissionResponse getMissionById(Long id);
    List<MissionResponse> getAllMissions();
    void deleteMission(Long id);
    MissionResponse updateMission(Long id, MissionRequest request);
}
