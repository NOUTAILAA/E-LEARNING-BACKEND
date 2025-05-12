package com.example.demo.entity;

public class EtatChapitreDTO {
    private Long id;
    private boolean etat;
    private Long apprenantId;
    private Long chapitreId;

    public EtatChapitreDTO(Long id, boolean etat, Long apprenantId, Long chapitreId) {
        this.id = id;
        this.etat = etat;
        this.apprenantId = apprenantId;
        this.chapitreId = chapitreId;
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEtat() { return etat; }
    public void setEtat(boolean etat) { this.etat = etat; }

    public Long getApprenantId() { return apprenantId; }
    public void setApprenantId(Long apprenantId) { this.apprenantId = apprenantId; }

    public Long getChapitreId() { return chapitreId; }
    public void setChapitreId(Long chapitreId) { this.chapitreId = chapitreId; }
}
