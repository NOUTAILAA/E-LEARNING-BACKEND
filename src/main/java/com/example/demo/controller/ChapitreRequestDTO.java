package com.example.demo.controller;

public class ChapitreRequestDTO {
    private String titre;
    private String dateCreation;
    private String dateMAJ;
    private int tempsEstimer;
    private Long coursId;

    // ✅ Constructeur sans paramètre (obligatoire pour Spring)
    public ChapitreRequestDTO() {}

    // ✅ Constructeur avec tous les paramètres
    public ChapitreRequestDTO(String titre, String dateCreation, String dateMAJ, int tempsEstimer, Long coursId) {
        this.titre = titre;
        this.dateCreation = dateCreation;
        this.dateMAJ = dateMAJ;
        this.tempsEstimer = tempsEstimer;
        this.coursId = coursId;
    }

    // ✅ Getters & Setters
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(String dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public int getTempsEstimer() {
        return tempsEstimer;
    }

    public void setTempsEstimer(int tempsEstimer) {
        this.tempsEstimer = tempsEstimer;
    }

    public Long getCoursId() {
        return coursId;
    }

    public void setCoursId(Long coursId) {
        this.coursId = coursId;
    }
}