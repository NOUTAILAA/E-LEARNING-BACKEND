package com.example.demo.entity;

public class EtatSectionResponseDTO {
    private Long id;
    private boolean etat;
    private Long apprenantId;
    private String apprenantNom;
    private Long sectionId;
    private String sectionTitre;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEtat() { return etat; }
    public void setEtat(boolean etat) { this.etat = etat; }

    public Long getApprenantId() { return apprenantId; }
    public void setApprenantId(Long apprenantId) { this.apprenantId = apprenantId; }

    public String getApprenantNom() { return apprenantNom; }
    public void setApprenantNom(String apprenantNom) { this.apprenantNom = apprenantNom; }

    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }

    public String getSectionTitre() { return sectionTitre; }
    public void setSectionTitre(String sectionTitre) { this.sectionTitre = sectionTitre; }
}