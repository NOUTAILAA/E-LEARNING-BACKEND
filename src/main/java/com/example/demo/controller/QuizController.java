package com.example.demo.controller;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Chapitre;
import com.example.demo.entity.Manager;
import com.example.demo.entity.PropositionDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.QuizDTO;
import com.example.demo.entity.QuizProjection;
import com.example.demo.entity.QuizRequestDTO;
import com.example.demo.service.ChapitreService;
import com.example.demo.service.ManagerService;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private ChapitreService chapitreService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private ManagerService managerService;
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
    @PutMapping("/{id}")
public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody QuizRequestDTO dto) {
    Quiz existing = quizService.getQuizById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }

    existing.setQuestion(dto.getQuestion());
    existing.setDateMAJ(new Date());

    Quiz updated = quizService.saveQuiz(existing);
    return ResponseEntity.ok(updated);
}

@PostMapping
public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequestDTO dto) {
    Chapitre chapitre = chapitreService.getChapitreById(dto.getChapitreId());
    if (chapitre == null) {
        return ResponseEntity.badRequest().build();
    }

    Quiz quiz = new Quiz();
    quiz.setQuestion(dto.getQuestion());
    quiz.setChapitre(chapitre);
    quiz.setDateCreation(new Date());
    quiz.setDateMAJ(new Date());

    Quiz saved = quizService.saveQuiz(quiz);
    return ResponseEntity.ok(saved);
}
    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

 @GetMapping("/chapitre/{chapitreId}")
public ResponseEntity<List<QuizProjection>> getQuizByChapitre(@PathVariable Long chapitreId) {
    return ResponseEntity.ok(quizService.getQuizLightByChapitreId(chapitreId));
}

  

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/{managerId}/apprenants-non-assignes")
public List<Apprenant> getApprenantsNonAssignesDuDepartement(@PathVariable Long managerId) {
    Manager manager = managerService.findById(managerId)
        .orElseThrow(() -> new IllegalArgumentException("Manager non trouvé"));
    Long departementId = manager.getDepartement().getId();
    return managerService.getApprenantsNonAssignesDansDepartement(departementId);
}
@GetMapping("/with-propositions/{id}")
public ResponseEntity<QuizDTO> getQuizWithPropositions(@PathVariable Long id) {
    Quiz quiz = quizService.getQuizById(id);
    if (quiz == null) return ResponseEntity.notFound().build();

    List<PropositionDTO> propositions = quiz.getPropositions().stream()
        .map(p -> new PropositionDTO(p.getId(), p.getReponse(), p.getCorrecte()))
        .toList();

    QuizDTO dto = new QuizDTO(quiz.getId(), quiz.getQuestion(), propositions);
    return ResponseEntity.ok(dto);
}

}
