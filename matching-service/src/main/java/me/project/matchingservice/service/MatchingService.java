package me.project.matchingservice.service;

import me.project.matchingservice.dto.Cv;
import me.project.matchingservice.dto.Mission;

public interface MatchingService {
    double calculateKeywordScore(Cv cv, Mission mission);
    double calculateMLScore(Cv cv, Mission mission);
}
