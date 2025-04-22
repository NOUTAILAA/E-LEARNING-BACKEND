package com.example.demo.entity;

public class SectionDTO {
    private Long id;
    private String titre;
    private String type;
    private String description;
    private String file;
    private String dateCreation;
    private String dateMAJ;
    private int tempsEstimer;
    private Long chapitreId;

    // Constructeur à partir de l'entité Section
    public SectionDTO(com.example.demo.entity.Section section) {
        this.id = section.getId();
        this.titre = section.getTitre();
        this.type = section.getType();
        this.description = section.getDescription();
        this.file = section.getFile();
        this.dateCreation = section.getDateCreation() != null ? section.getDateCreation().toString() : null;
        this.dateMAJ = section.getDateMAJ() != null ? section.getDateMAJ().toString() : null;
        this.tempsEstimer = section.getTempsEstimer();
        this.chapitreId = section.getChapitre() != null ? section.getChapitre().getId() : null;
    }

    public SectionDTO() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}



