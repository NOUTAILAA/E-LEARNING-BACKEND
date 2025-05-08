package com.example.demo.service;

import com.example.demo.entity.ReponseQuiz;
import com.example.demo.entity.ReponseQuizId;
import com.example.demo.repository.ReponseQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseQuizService {

    @Autowired
    private ReponseQuizRepository reponseQuizRepository;

    public ReponseQuiz save(ReponseQuiz reponse) {
        return reponseQuizRepository.save(reponse);
    }

    public List<ReponseQuiz> getByApprenant(Long apprenantId) {
        return reponseQuizRepository.findByApprenantId(apprenantId);
    }

    public List<ReponseQuiz> getByQuiz(Long quizId) {
        return reponseQuizRepository.findByQuizId(quizId);
    }

    public List<ReponseQuiz> getByApprenantAndQuiz(Long apprenantId, Long quizId) {
        return reponseQuizRepository.findByApprenantIdAndQuizId(apprenantId, quizId);
    }

    public void delete(ReponseQuizId id) {
        reponseQuizRepository.deleteById(id);
    }
    public double calculerScorePourApprenantEtQuiz(Long apprenantId, Long quizId) {
        List<Boolean> correctFlags = reponseQuizRepository.findCorrectFlagsByApprenantAndQuiz(apprenantId, quizId);
    
        if (correctFlags.isEmpty()) return 0.0;
    
        long total = correctFlags.size();
        long bonnes = correctFlags.stream().filter(Boolean::booleanValue).count();
    
        return (bonnes * 100.0) / total;
    }
    
    
}
