package me.project.missionservice.service;

import lombok.RequiredArgsConstructor;
import me.project.missionservice.dto.MissionRequest;
import me.project.missionservice.dto.MissionResponse;
import me.project.missionservice.entity.Mission;
import me.project.missionservice.mapper.MissionMapper;
import me.project.missionservice.repository.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository repository;


    @Override
    public MissionResponse createMission(MissionRequest request) {
        Mission mission = Mission.builder()
                .description(request.getDescription())
                .exigences(request.getExigences())
                .build();
        return mapToResponse(repository.save(mission));
    }

    @Override
    public MissionResponse getMissionById(Long id) {
        Mission mission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found"));
        return mapToResponse(mission);
    }

    @Override
    public List<MissionResponse> getAllMissions() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMission(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MissionResponse updateMission(Long id, MissionRequest request) {
        Mission mission = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found with id: " + id));

        mission.setDescription(request.getDescription());
        mission.setExigences(request.getExigences());

        Mission updated = repository.save(mission);
        return MissionMapper.mapToResponse(updated);
    }

    private MissionResponse mapToResponse(Mission mission) {
        return new MissionResponse(mission.getId(), mission.getDescription(), mission.getExigences());
    }
}
