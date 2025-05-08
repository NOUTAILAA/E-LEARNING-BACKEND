package com.example.demo.controller;

import com.example.demo.entity.Proposition;
import com.example.demo.entity.PropositionProjection;
import com.example.demo.entity.Quiz;
import com.example.demo.service.PropositionService;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propositions")
public class PropositionController {

    @Autowired
    private PropositionService propositionService;

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<Proposition> addProposition(@RequestParam Long quizId, @RequestBody Proposition prop) {
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) return ResponseEntity.badRequest().build();

        prop.setQuiz(quiz);
        return ResponseEntity.ok(propositionService.save(prop));
    }

   @GetMapping("/quiz/{quizId}")
public ResponseEntity<List<PropositionProjection>> getByQuiz(@PathVariable Long quizId) {
    return ResponseEntity.ok(propositionService.getLightByQuizId(quizId));
}




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        propositionService.delete(id);
    }
}
