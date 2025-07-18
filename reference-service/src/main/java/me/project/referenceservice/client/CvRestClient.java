package me.project.referenceservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import me.project.referenceservice.dto.Cv;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CV-SERVICE")
public interface CvRestClient {

    @GetMapping("/api/cvs/{id}")
    @CircuitBreaker(name = "cvService", fallbackMethod = "getDefaultCv")
    Cv findCvById(@PathVariable Long id);

    @GetMapping("/api/cvs")
    @CircuitBreaker(name = "cvService", fallbackMethod = "getDefaultCvs")
    List<Cv> findAllCvs();

    default Cv getDefaultCv(Long id, Exception exception){
        Cv cv = new Cv();
        cv.setId(id);
        cv.setTitre("Unavailable");
        return cv;
    }

    default List<Cv> getDefaultCvs(Exception exception){
        return List.of();
    }
}