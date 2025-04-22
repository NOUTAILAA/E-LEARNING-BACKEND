package com.example.demo.entity;

public interface SectionProjection {
    Long getId();
    String getTitre();
    String getType();
    String getDescription();
    String getFile();
    String getDateCreation();
    String getDateMAJ();
    int getTempsEstimer();
    Long getChapitreId();
}