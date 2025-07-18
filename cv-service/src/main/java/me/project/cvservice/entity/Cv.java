package me.project.cvservice.entity;

import jakarta.persistence.*;
import lombok.*;
import me.project.cvservice.Enum.Statut;
import me.project.cvservice.dto.User;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    @Column(columnDefinition = "TEXT")
    private String resume;
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
    @Enumerated(EnumType.STRING)
    private Statut statut;
    private LocalDate dateCreation;
    @Transient
    private User user;
    private Long userId;
}