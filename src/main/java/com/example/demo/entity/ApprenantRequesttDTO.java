package com.example.demo.entity;

public class ApprenantRequesttDTO {
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String telephone;
    private String sexe;
    private String email;
    private Long departementId;
    private Long managerId; 

    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }



    public Long getDepartementId() { return departementId; }
    public void setDepartementId(Long departementId) { this.departementId = departementId; }
}