package com.example.demo.controller;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Quiz;
import com.example.demo.service.ManagerService;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private ManagerService managerService;
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/chapitre/{chapitreId}")
    public List<Quiz> getQuizzesByChapitre(@PathVariable Long chapitreId) {
        return quizService.getQuizzesByChapitreId(chapitreId);
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
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

}
