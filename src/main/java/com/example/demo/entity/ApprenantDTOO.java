package com.example.demo.entity;

public class ApprenantDTOO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String sexe;
    private String dateNaissance;

    private Long departementId;
    private String departementNom;

    private Long managerId;

    // Getters et Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Long getDepartementId() {
        return departementId;
    }
    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    public String getDepartementNom() {
        return departementNom;
    }
    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
