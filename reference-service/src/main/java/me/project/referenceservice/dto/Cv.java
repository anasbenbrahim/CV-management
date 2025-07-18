package me.project.referenceservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Cv {
    private Long id;
    private String titre;
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
    private LocalDate dateCreation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public int getAnneesExperience() {
        return anneesExperience;
    }

    public void setAnneesExperience(int anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getTypeContratSouhaite() {
        return typeContratSouhaite;
    }

    public void setTypeContratSouhaite(String typeContratSouhaite) {
        this.typeContratSouhaite = typeContratSouhaite;
    }

    public String getMobilite() {
        return mobilite;
    }

    public void setMobilite(String mobilite) {
        this.mobilite = mobilite;
    }

    public String getPretentionSalariale() {
        return pretentionSalariale;
    }

    public void setPretentionSalariale(String pretentionSalariale) {
        this.pretentionSalariale = pretentionSalariale;
    }

    public String getLienLinkedIn() {
        return lienLinkedIn;
    }

    public void setLienLinkedIn(String lienLinkedIn) {
        this.lienLinkedIn = lienLinkedIn;
    }

    public String getLienPortfolio() {
        return lienPortfolio;
    }

    public void setLienPortfolio(String lienPortfolio) {
        this.lienPortfolio = lienPortfolio;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}

