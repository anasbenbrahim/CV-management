package me.project.matchingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mission {
    private Long id;
    private String description;
    private String exigences;
}
