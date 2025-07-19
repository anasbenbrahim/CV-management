package me.project.missionservice.mapper;

import me.project.missionservice.dto.MissionResponse;
import me.project.missionservice.entity.Mission;

public class MissionMapper {

    public static MissionResponse mapToResponse(Mission mission) {
        return MissionResponse.builder()
                .id(mission.getId())
                .description(mission.getDescription())
                .exigences(mission.getExigences())
                .build();
    }
}
