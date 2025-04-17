package com.example.demo.entity;

public class CoursRequestDTO {
    private String titre;
    private String description;
    private int tempsEstimer;
    private Long projetId;

    // Getters & Setters
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTempsEstimer() { return tempsEstimer; }
    public void setTempsEstimer(int tempsEstimer) { this.tempsEstimer = tempsEstimer; }

    public Long getProjetId() { return projetId; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }
}
