package com.example.demo.controller;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Proposition;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.ReponseQuiz;
import com.example.demo.service.ApprenantService;
import com.example.demo.service.PropositionService;
import com.example.demo.service.QuizService;
import com.example.demo.service.ReponseQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reponses")
public class ReponseQuizController {

    @Autowired
    private ReponseQuizService reponseQuizService;

    @Autowired
    private ApprenantService apprenantService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private PropositionService propositionService;

    @PostMapping
    public ResponseEntity<ReponseQuiz> addResponse(
        @RequestParam Long apprenantId,
        @RequestParam Long quizId,
        @RequestParam Long propositionId
    ) {
        Apprenant apprenant = apprenantService.getApprenantById(apprenantId);
        Quiz quiz = quizService.getQuizById(quizId);
        Proposition proposition = propositionService.getById(propositionId);

        if (apprenant == null || quiz == null || proposition == null) {
            return ResponseEntity.badRequest().build();
        }

        ReponseQuiz reponse = new ReponseQuiz();
        reponse.setApprenant(apprenant);
        reponse.setQuiz(quiz);
        reponse.setProposition(proposition);
        reponse.setCorrect(proposition.getCorrecte());

        return ResponseEntity.ok(reponseQuizService.save(reponse));
    }

    @GetMapping("/apprenant/{id}")
    public List<ReponseQuiz> getByApprenant(@PathVariable Long id) {
        return reponseQuizService.getByApprenant(id);
    }

    @GetMapping("/quiz/{id}")
    public List<ReponseQuiz> getByQuiz(@PathVariable Long id) {
        return reponseQuizService.getByQuiz(id);
    }

    @GetMapping("/apprenant/{apprenantId}/quiz/{quizId}")
    public List<ReponseQuiz> getByApprenantAndQuiz(@PathVariable Long apprenantId, @PathVariable Long quizId) {
        return reponseQuizService.getByApprenantAndQuiz(apprenantId, quizId);
    }
    @GetMapping("/score")
public ResponseEntity<Double> getScore(
    @RequestParam Long apprenantId,
    @RequestParam Long quizId) {

    double score = reponseQuizService.calculerScorePourApprenantEtQuiz(apprenantId, quizId);
    return ResponseEntity.ok(score);
}

}
