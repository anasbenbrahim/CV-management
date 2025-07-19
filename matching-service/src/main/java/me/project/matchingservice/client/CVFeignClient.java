package me.project.matchingservice.client;

import me.project.matchingservice.dto.Cv;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cv-service")
public interface CVFeignClient {
    @GetMapping("/api/cvs/{id}")
    Cv getCVById(@PathVariable("id") Long id);
}
