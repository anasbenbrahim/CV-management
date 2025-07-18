package me.project.cvservice.service;

import me.project.cvservice.dto.CvRequest;
import me.project.cvservice.dto.CvResponse;
import me.project.cvservice.entity.Cv;

import java.util.List;

public interface CvService {
    String createCv(CvRequest request);
    void updateCv(CvRequest request);
    void deleteCv(Long id);
    List<CvResponse> listCv();
    CvResponse getCvById(Long id);
    Cv achiveCv(Long id);

}
