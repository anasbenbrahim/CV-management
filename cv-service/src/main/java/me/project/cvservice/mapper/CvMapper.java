package me.project.cvservice.mapper;

import me.project.cvservice.dto.CvRequest;
import me.project.cvservice.dto.CvResponse;
import me.project.cvservice.entity.Cv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CvMapper {

    @Mapping(target = "user", ignore = true) // Ignore user field, populated in service
    @Mapping(source = "userId", target = "userId") // Map userId explicitly
    CvResponse fromCv(Cv cv);

    @Mapping(source = "userId", target = "userId") // Map userId explicitly
    Cv toCv(CvRequest request);
}