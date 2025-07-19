package me.project.matchingservice.controller;

import lombok.RequiredArgsConstructor;
import me.project.matchingservice.client.CVFeignClient;
import me.project.matchingservice.client.MissionFeignClient;
import me.project.matchingservice.dto.Cv;
import me.project.matchingservice.dto.MatchingResponse;
import me.project.matchingservice.dto.Mission;
import me.project.matchingservice.service.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final CVFeignClient cvFeignClient;
    private final MissionFeignClient missionFeignClient;
    private final MatchingService matchingService;

    @GetMapping("/score")
    public ResponseEntity<MatchingResponse> getMatchingScore(
            @RequestParam Long cvId,
            @RequestParam Long missionId) {

        Cv cv = cvFeignClient.getCVById(cvId);
        Mission mission = missionFeignClient.getMissionById(missionId);

        double keywordScore = matchingService.calculateKeywordScore(cv, mission);
        double mlScore = matchingService.calculateMLScore(cv, mission);

        return ResponseEntity.ok(new MatchingResponse(cvId, missionId, keywordScore, mlScore));
    }
}
