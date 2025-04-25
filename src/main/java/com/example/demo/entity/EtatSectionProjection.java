package com.example.demo.entity;

public interface EtatSectionProjection {
    Long getId();
    Boolean getEtat();
    Long getApprenantId();
    String getApprenantNom();
    Long getSectionId();
    String getSectionTitre();
}