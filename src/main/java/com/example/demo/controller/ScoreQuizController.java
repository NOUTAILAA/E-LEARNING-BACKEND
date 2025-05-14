package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ScoreQuiz;
import com.example.demo.entity.ScoreQuizRequestDTO;
import com.example.demo.service.ScoreQuizService;

import java.util.List;

@RestController
@RequestMapping("/api/score-quiz")
public class ScoreQuizController {

    @Autowired
    private ScoreQuizService scoreQuizService;

    @PostMapping
    public ScoreQuiz enregistrerScore(@RequestBody ScoreQuizRequestDTO dto) {
        return scoreQuizService.saveScore(dto);
    }

    @GetMapping
    public List<ScoreQuiz> getAllScores() {
        return scoreQuizService.getAllScores();
    }

    @GetMapping("/apprenant/{apprenantId}")
    public List<ScoreQuiz> getScoresByApprenant(@PathVariable Long apprenantId) {
        return scoreQuizService.getScoresByApprenantId(apprenantId);
    }
}
