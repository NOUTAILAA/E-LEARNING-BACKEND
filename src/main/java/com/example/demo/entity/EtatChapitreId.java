package com.example.demo.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EtatChapitreId implements Serializable {

    private Long apprenantId;
    private Long chapitreId;

    // Constructors
    public EtatChapitreId() {}

    public EtatChapitreId(Long apprenantId, Long chapitreId) {
        this.apprenantId = apprenantId;
        this.chapitreId = chapitreId;
    }

    // Getters and Setters
    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getChapitreId() {
        return chapitreId;
    }

    public void setChapitreId(Long chapitreId) {
        this.chapitreId = chapitreId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatChapitreId)) return false;
        EtatChapitreId that = (EtatChapitreId) o;
        return Objects.equals(apprenantId, that.apprenantId) &&
               Objects.equals(chapitreId, that.chapitreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenantId, chapitreId);
    }
}
