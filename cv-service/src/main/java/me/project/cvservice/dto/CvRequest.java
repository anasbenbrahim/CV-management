package me.project.cvservice.dto;

import me.project.cvservice.Enum.Statut;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDate;

public record CvRequest(
        Long id,
        @NotNull(message = "Le titre est requis")
        @Size(min = 2, max = 100, message = "Le titre doit contenir entre 2 et 100 caractères")
        String titre,
        @NotNull(message = "Le résumé est requis")
        @Size(min = 10, max = 1000, message = "Le résumé doit contenir entre 10 et 1000 caractères")
        String resume,
        @NotNull(message = "Le secteur d'activité est requis")
        String secteurActivite,
        @NotNull(message = "Le niveau d'étude est requis")
        String niveauEtude,
        @Min(value = 0, message = "Les années d'expérience ne peuvent pas être négatives")
        int anneesExperience,
        @NotNull(message = "La localisation est requise")
        String localisation,
        @NotNull(message = "La langue est requise")
        String langue,
        @NotNull(message = "Le type de contrat souhaité est requis")
        String typeContratSouhaite,
        @NotNull(message = "La mobilité est requise")
        String mobilite,
        @NotNull(message = "La prétention salariale est requise")
        String pretentionSalariale,
        @URL(message = "Le lien LinkedIn doit être une URL valide")
        String lienLinkedIn,
        @URL(message = "Le lien du portfolio doit être une URL valide")
        String lienPortfolio,
        @NotNull(message = "Le statut est requis")
        Statut statut,
        @NotNull(message = "La date de création est requise")
        LocalDate dateCreation,
        @NotNull
        Long userId
) {
}