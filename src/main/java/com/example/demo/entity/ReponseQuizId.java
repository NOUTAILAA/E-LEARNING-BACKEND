package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReponseQuizId implements Serializable {

    private Long apprenant;
    private Long quiz;
    private Long proposition;

    public ReponseQuizId() {}

    public ReponseQuizId(Long apprenant, Long quiz, Long proposition) {
        this.apprenant = apprenant;
        this.quiz = quiz;
        this.proposition = proposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReponseQuizId)) return false;
        ReponseQuizId that = (ReponseQuizId) o;
        return Objects.equals(apprenant, that.apprenant) &&
               Objects.equals(quiz, that.quiz) &&
               Objects.equals(proposition, that.proposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apprenant, quiz, proposition);
    }
}
