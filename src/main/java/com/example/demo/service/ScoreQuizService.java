
// ✅ ScoreQuizService.java
package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreQuizService {

    @Autowired
    private ApprenantRepository apprenantRepository;

    @Autowired
    private ScoreQuizRepository scoreQuizRepository;
@Autowired
private QuizRepository quizChapitreRepository;
    @Autowired
    private QuizSectionRepository quizSectionRepository;

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
        return scoreQuizRepository.findByQuizSectionId(quizId);
    }

    public Optional<ScoreQuiz> getScoreByApprenantAndQuiz(Long apprenantId, Long quizId) {
        return scoreQuizRepository.findByApprenantIdAndQuizSectionId(apprenantId, quizId);
    }

    public ScoreQuiz createScore(ScoreQuiz score) {
        return scoreQuizRepository.save(score);
    }

    public ScoreQuiz updateScore(Long id, ScoreQuiz updated) {
        return scoreQuizRepository.findById(id).map(score -> {
            score.setApprenant(updated.getApprenant());
            score.setQuizSection(updated.getQuizSection());
            score.setScore(updated.getScore());
            return scoreQuizRepository.save(score);
        }).orElse(null);
    }

    public void deleteScore(Long id) {
        scoreQuizRepository.deleteById(id);
    }

public ScoreQuiz saveScore(ScoreQuizRequestDTO dto) {
    ScoreQuiz score = new ScoreQuiz();

    Optional<Apprenant> apprenantOpt = apprenantRepository.findById(dto.getApprenantId());
    if (apprenantOpt.isEmpty()) {
        throw new RuntimeException("Apprenant non trouvé avec ID : " + dto.getApprenantId());
    }

    score.setApprenant(apprenantOpt.get());
    score.setScore(dto.getScore());

    if (dto.getQuizSectionId() != null) {
        Optional<QuizSection> quizOpt = quizSectionRepository.findById(dto.getQuizSectionId());
        if (quizOpt.isEmpty()) {
            throw new RuntimeException("QuizSection non trouvé avec ID : " + dto.getQuizSectionId());
        }
        score.setQuizSection(quizOpt.get());
    }

    if (dto.getQuizChapitreId() != null) {
        Optional<Quiz> quizChapOpt = quizChapitreRepository.findById(dto.getQuizChapitreId());
        if (quizChapOpt.isEmpty()) {
            throw new RuntimeException("QuizChapitre non trouvé avec ID : " + dto.getQuizChapitreId());
        }
        score.setQuizChapitre(quizChapOpt.get());
    }

    return scoreQuizRepository.save(score);
}
}