package me.project.matchingservice.client;

import me.project.matchingservice.dto.Mission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mission-service")
public interface MissionFeignClient {
    @GetMapping("/api/missions/{id}")
    Mission getMissionById(@PathVariable("id") Long id);
}