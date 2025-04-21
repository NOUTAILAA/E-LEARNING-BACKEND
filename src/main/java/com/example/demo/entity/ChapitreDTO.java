package com.example.demo.entity;

public class ChapitreDTO {
    private Long id;
    private String titre;
    private int tempsEstimer;
    private String dateCreation;
    private String dateMAJ;
    private Long coursId;

    public ChapitreDTO(Chapitre chapitre) {
        this.id = chapitre.getId();
        this.titre = chapitre.getTitre();
        this.tempsEstimer = chapitre.getTempsEstimer();
        this.dateCreation = chapitre.getDateCreation() != null ? chapitre.getDateCreation().toString() : null;
        this.dateMAJ = chapitre.getDateMAJ() != null ? chapitre.getDateMAJ().toString() : null;
        this.coursId = chapitre.getCours() != null ? chapitre.getCours().getId() : null;
    }

    // getters/setters
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

public int getTempsEstimer() {
    return tempsEstimer;
}

public void setTempsEstimer(int tempsEstimer) {
    this.tempsEstimer = tempsEstimer;
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

public Long getCoursId() {
    return coursId;
}

public void setCoursId(Long coursId) {
    this.coursId = coursId;
}
public ChapitreDTO(){}
}
