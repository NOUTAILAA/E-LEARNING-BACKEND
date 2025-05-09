package com.example.demo.service;

import com.example.demo.entity.ReponseQuizSection;
import com.example.demo.entity.ReponseQuizSectionId;
import com.example.demo.repository.ReponseQuizSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseQuizSectionService {

    @Autowired
    private ReponseQuizSectionRepository repository;

    public ReponseQuizSection save(ReponseQuizSection reponse) {
        return repository.save(reponse);
    }

    public List<ReponseQuizSection> getByApprenant(Long apprenantId) {
        return repository.findByApprenantId(apprenantId);
    }

    public List<ReponseQuizSection> getByQuiz(Long quizSectionId) {
        return repository.findByQuizSectionId(quizSectionId);
    }

    public List<ReponseQuizSection> getByApprenantAndQuiz(Long apprenantId, Long quizSectionId) {
        return repository.findByApprenantIdAndQuizSectionId(apprenantId, quizSectionId);
    }

    public double calculerScorePourApprenantEtQuiz(Long apprenantId, Long quizSectionId) {
        List<Boolean> correctFlags = repository.findCorrectFlagsByApprenantAndQuiz(apprenantId, quizSectionId);

        if (correctFlags.isEmpty()) return 0.0;

        long total = correctFlags.size();
        long bonnes = correctFlags.stream().filter(Boolean::booleanValue).count();

        return (bonnes * 100.0) / total;
    }

    public void delete(ReponseQuizSectionId id) {
        repository.deleteById(id);
    }
}
