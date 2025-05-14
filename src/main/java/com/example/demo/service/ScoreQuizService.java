package com.example.demo.service;

import com.example.demo.entity.ScoreQuiz;
import com.example.demo.repository.ScoreQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ScoreQuizRequestDTO;
import com.example.demo.entity.Apprenant;
import com.example.demo.repository.ApprenantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreQuizService {
@Autowired
private ApprenantRepository apprenantRepository;

    @Autowired
    private ScoreQuizRepository scoreQuizRepository;

    public List<ScoreQuiz> getAllScores() {
        return scoreQuizRepository.findAll();
    }

    public Optional<ScoreQuiz> getScoreById(Long id) {
        return scoreQuizRepository.findById(id);
    }

    public List<ScoreQuiz> getScoresByApprenantId(Long apprenantId) {
        return scoreQuizRepository.findByApprenantId(apprenantId);
    }

    public List<ScoreQuiz> getScoresByQuizId(Long quizId) {
        return scoreQuizRepository.findByQuizId(quizId);
    }

    public Optional<ScoreQuiz> getScoreByApprenantAndQuiz(Long apprenantId, Long quizId) {
        return scoreQuizRepository.findByApprenantIdAndQuizId(apprenantId, quizId);
    }

    public ScoreQuiz createScore(ScoreQuiz score) {
        return scoreQuizRepository.save(score);
    }

    public ScoreQuiz updateScore(Long id, ScoreQuiz updated) {
        return scoreQuizRepository.findById(id).map(score -> {
            score.setApprenant(updated.getApprenant());
            score.setQuizId(updated.getQuizId());
            score.setScore(updated.getScore());
            return scoreQuizRepository.save(score);
        }).orElse(null);
    }

    public void deleteScore(Long id) {
        scoreQuizRepository.deleteById(id);
    }
public ScoreQuiz saveScore(ScoreQuizRequestDTO dto) {
    ScoreQuiz score = new ScoreQuiz();

    // Récupérer l'objet Apprenant à partir de l'ID
    Optional<Apprenant> apprenantOpt = apprenantRepository.findById(dto.getApprenant());
    if (apprenantOpt.isEmpty()) {
        throw new RuntimeException("Apprenant non trouvé avec ID : " + dto.getApprenant());
    }

    score.setApprenant(apprenantOpt.get()); // Apprenant attendu
    score.setQuizId(dto.getQuizId());
    score.setScore(dto.getScore());

    return scoreQuizRepository.save(score);
}


}
