package me.project.matchingservice.service;

import lombok.RequiredArgsConstructor;
import me.project.matchingservice.dto.Cv;
import me.project.matchingservice.dto.Mission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final RestTemplate restTemplate;
    @Value("${ml.service.url:http://localhost:5000/predict}")
    private String mlServiceUrl; // Moved @Value here, injected by Spring

    @Override
    public double calculateKeywordScore(Cv cv, Mission mission) {
        String cvText = cv.getTitre().toLowerCase();
        String missionText = (mission.getDescription() + " " + mission.getExigences()).toLowerCase();

        Set<String> cvWords = new HashSet<>(Arrays.asList(cvText.split("\\W+")));
        Set<String> missionWords = new HashSet<>(Arrays.asList(missionText.split("\\W+")));

        Set<String> intersection = new HashSet<>(cvWords);
        intersection.retainAll(missionWords);

        return missionWords.isEmpty() ? 0.0 : (double) intersection.size() / missionWords.size();
    }

    @Override
    public double calculateMLScore(Cv cv, Mission mission) {
        String cvText = cv.getTitre();
        String missionText = mission.getDescription() + " " + mission.getExigences();
        System.out.println("ML Request URL: " + mlServiceUrl + "?cv_text=" + URLEncoder.encode(cvText, StandardCharsets.UTF_8)
                + "&mission_text=" + URLEncoder.encode(missionText, StandardCharsets.UTF_8));

        try {
            String url = mlServiceUrl + "?cv_text=" + URLEncoder.encode(cvText, StandardCharsets.UTF_8)
                    + "&mission_text=" + URLEncoder.encode(missionText, StandardCharsets.UTF_8);
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Double score = (Double) response.getBody().get("score");
                System.out.println("ML Response: " + score);
                return score != null ? score : 0.0;
            }
            System.out.println("ML Response invalid or null");
            return 0.0;
        } catch (RestClientException e) {
            System.err.println("Failed to connect to ML service: " + e.getMessage());
            return 0.0;
        } catch (Exception e) {
            System.err.println("Error processing ML score: " + e.getMessage());
            return 0.0;
        }
    }
}