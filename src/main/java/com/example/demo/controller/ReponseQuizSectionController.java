package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reponses-section")
public class ReponseQuizSectionController {

    @Autowired
    private ReponseQuizSectionService reponseService;

    @Autowired
    private ApprenantService apprenantService;

    @Autowired
    private QuizSectionService quizSectionService;

    @Autowired
    private PropositionSectionService propositionSectionService;

    @PostMapping
    public ResponseEntity<ReponseQuizSection> addResponse(
        @RequestParam Long apprenantId,
        @RequestParam Long quizSectionId,
        @RequestParam Long propositionId
    ) {
        Apprenant apprenant = apprenantService.getApprenantById(apprenantId);
        QuizSection quiz = quizSectionService.getById(quizSectionId);
        PropositionSection proposition = propositionSectionService.getById(propositionId);

        if (apprenant == null || quiz == null || proposition == null) {
            return ResponseEntity.badRequest().build();
        }

        ReponseQuizSection reponse = new ReponseQuizSection();
        reponse.setApprenant(apprenant);
        reponse.setQuizSection(quiz);
        reponse.setProposition(proposition);
        reponse.setCorrect(proposition.getCorrecte());

        return ResponseEntity.ok(reponseService.save(reponse));
    }

    @GetMapping("/apprenant/{id}")
    public List<ReponseQuizSection> getByApprenant(@PathVariable Long id) {
        return reponseService.getByApprenant(id);
    }

    @GetMapping("/quiz/{id}")
    public List<ReponseQuizSection> getByQuiz(@PathVariable Long id) {
        return reponseService.getByQuiz(id);
    }

    @GetMapping("/apprenant/{apprenantId}/quiz/{quizId}")
    public List<ReponseQuizSection> getByApprenantAndQuiz(@PathVariable Long apprenantId, @PathVariable Long quizId) {
        return reponseService.getByApprenantAndQuiz(apprenantId, quizId);
    }

    @GetMapping("/score")
    public ResponseEntity<Double> getScore(
        @RequestParam Long apprenantId,
        @RequestParam Long quizSectionId) {

        double score = reponseService.calculerScorePourApprenantEtQuiz(apprenantId, quizSectionId);
        return ResponseEntity.ok(score);
    }
}
