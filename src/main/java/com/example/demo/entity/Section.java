package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type;
    @Column(length = 10000)
    private String description;
    private String file;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    private Date dateMAJ;

    private int tempsEstimer;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;

    // ====== Getters & Setters ======
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public int getTempsEstimer() {
        return tempsEstimer;
    }

    public void setTempsEstimer(int tempsEstimer) {
        this.tempsEstimer = tempsEstimer;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }
}
