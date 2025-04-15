package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjetDTO {
    private Long id;
    private String nom;
    private String nomClient;
    private String description;
    private Long managerId;
    private Long departementId;
    public ProjetDTO() {}  // ✅ Nécessaire !

    // Constructeur à partir d'une entité Projet
    public ProjetDTO(Projet projet) {
        this.id = projet.getId();
        this.nom = projet.getNom();
        this.nomClient = projet.getNomClient();
        this.description = projet.getDescription();
        this.managerId = projet.getManager() != null ? projet.getManager().getId() : null;
        this.departementId = projet.getDepartement() != null ? projet.getDepartement().getId() : null;
    }

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

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }
    
    
}
