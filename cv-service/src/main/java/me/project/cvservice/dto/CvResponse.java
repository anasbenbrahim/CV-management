package me.project.cvservice.dto;

import me.project.cvservice.Enum.Statut;
import me.project.cvservice.dto.User;
import java.time.LocalDate;

public record CvResponse(
        Long id,
        String titre,
        String resume,
        String secteurActivite,
        String niveauEtude,
        int anneesExperience,
        String localisation,
        String langue,
        String typeContratSouhaite,
        String mobilite,
        String pretentionSalariale,
        String lienLinkedIn,
        String lienPortfolio,
        Statut statut,
        LocalDate dateCreation,
        Long userId,
        User user
) {
}