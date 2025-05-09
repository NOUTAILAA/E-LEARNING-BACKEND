package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReponseQuizSectionId implements Serializable {
    private Long apprenant;
    private Long quizSection;
    private Long proposition;

    // equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReponseQuizSectionId that)) return false;
        return Objects.equals(apprenant, that.apprenant) &&
               Objects.equals(quizSection, that.quizSection) &&
               Objects.equals(proposition, that.proposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenant, quizSection, proposition);
    }
}
