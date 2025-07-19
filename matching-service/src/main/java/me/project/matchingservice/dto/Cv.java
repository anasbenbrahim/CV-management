package me.project.matchingservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cv {
    private Long id;
    private String titre;
    private String secteurActivite;
    private String niveauEtude;
    private int anneesExperience;
    private String localisation;
    private String langue;
    private String typeContratSouhaite;
    private String mobilite;
    private String pretentionSalariale;
    private String lienLinkedIn;
    private String lienPortfolio;
}
