package com.example.demo.entity;

public class SectionRequestDTO {
    private String titre;
    private String type;
    private String description;
    private String file;
    private String dateCreation;
    private String dateMAJ;
    private int tempsEstimer;
    private Long chapitreId;

    // Getters & Setters
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFile() { return file; }
    public void setFile(String file) { this.file = file; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }

    public String getDateMAJ() { return dateMAJ; }
    public void setDateMAJ(String dateMAJ) { this.dateMAJ = dateMAJ; }

    public int getTempsEstimer() { return tempsEstimer; }
    public void setTempsEstimer(int tempsEstimer) { this.tempsEstimer = tempsEstimer; }

    public Long getChapitreId() { return chapitreId; }
    public void setChapitreId(Long chapitreId) { this.chapitreId = chapitreId; }
    public SectionRequestDTO(){}

}

