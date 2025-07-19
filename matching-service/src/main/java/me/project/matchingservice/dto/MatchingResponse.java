package me.project.matchingservice.dto;

import lombok.Value;

@Value
public class MatchingResponse {
    Long cvId;
    Long missionId;
    double keywordScore;
    double mlScore;
}
