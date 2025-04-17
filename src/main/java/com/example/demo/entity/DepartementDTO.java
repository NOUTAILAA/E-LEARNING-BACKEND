package com.example.demo.entity;
public class DepartementDTO {
    private Long id;
    private String nom;

    public DepartementDTO() {}

    public DepartementDTO(Departement dep) {
        this.id = dep.getId();
        this.nom = dep.getNom();
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
