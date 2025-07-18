package me.project.referenceservice.mapper;

import me.project.referenceservice.dto.ReferenceRequest;
import me.project.referenceservice.dto.ReferenceResponse;
import me.project.referenceservice.entity.Reference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReferenceMapper {

    // Pas besoin de mapper un champ 'cv', car on l'ajoutera manuellement ensuite
    ReferenceResponse fromReference(Reference reference);

    Reference toReference(ReferenceRequest request);
}
