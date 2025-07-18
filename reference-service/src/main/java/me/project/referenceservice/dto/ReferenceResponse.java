package me.project.referenceservice.dto;

public record ReferenceResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String company,
        String position,
        String recommendation,
        Long cvId,
        Cv cv
) {
}
