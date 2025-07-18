package me.project.referenceservice.dto;

import jakarta.validation.constraints.*;

public record ReferenceRequest(
        Long id,

        @NotBlank(message = "Le nom complet est requis")
        @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
        String fullName,

        @NotBlank(message = "L'email est requis")
        @Email(message = "Email invalide")
        String email,

        @NotBlank(message = "Le numéro de téléphone est requis")
        @Pattern(regexp = "^[0-9+\\-\\s]*$", message = "Numéro de téléphone invalide")
        String phone,

        @NotBlank(message = "Le nom de l'entreprise est requis")
        @Size(min = 2, max = 100, message = "Le nom de l'entreprise doit contenir entre 2 et 100 caractères")
        String company,

        @NotBlank(message = "Le poste est requis")
        @Size(min = 2, max = 100, message = "Le poste doit contenir entre 2 et 100 caractères")
        String position,

        @NotBlank(message = "La recommandation est requise")
        @Size(min = 10, max = 2000, message = "La recommandation doit contenir entre 10 et 2000 caractères")
        String recommendation,

        @NotNull(message = "L'identifiant du CV est requis")
        Long cvId
) {
}
