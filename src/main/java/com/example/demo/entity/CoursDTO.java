package com.example.demo.entity;

public class CoursDTO {
    private Long id;
    private String titre;
    private String description;
    private Long projetId;
    private int tempsEstimer;
    private String projetNom;

    public CoursDTO() {}

    public CoursDTO(Cours cours) {
        this.id = cours.getId();
        this.titre = cours.getTitre();
        this.description = cours.getDescription();
        this.tempsEstimer = cours.getTempsEstimer();
        this.projetId = cours.getProjet() != null ? cours.getProjet().getId() : null;
        this.projetNom = cours.getProjet() != null ? cours.getProjet().getNom() : null;
    }

    // ✅ Getters
    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public Long getProjetId() { return projetId; }
    public int getTempsEstimer() { return tempsEstimer; }
    public String getProjetNom() { return projetNom; }

    // ✅ Setters
    public void setId(Long id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }
    public void setTempsEstimer(int tempsEstimer) { this.tempsEstimer = tempsEstimer; }
    public void setProjetNom(String projetNom) { this.projetNom = projetNom; }
    public CoursDTO(Long id, String titre, String description, int tempsEstimer, Long projetId, String projetNom) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.tempsEstimer = tempsEstimer;
        this.projetId = projetId;
        this.projetNom = projetNom;
    }
    
}
