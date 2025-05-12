package com.example.demo.controller;

import com.example.demo.entity.QuizSection;
import com.example.demo.entity.QuizSectionDTO;
import com.example.demo.entity.QuizSectionRequestDTO;
import com.example.demo.service.QuizSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizsection")
@CrossOrigin(origins = "*")
public class QuizSectionController {

    @Autowired
    private QuizSectionService quizSectionService;

@GetMapping("/section/{sectionId}")
public List<QuizSectionDTO> getQuizzesBySection(@PathVariable Long sectionId) {
    return quizSectionService.getQuizDTOBySectionId(sectionId);
}



    @PostMapping
    public QuizSection addQuiz(@RequestParam String question, @RequestParam Long sectionId) {
        return quizSectionService.addQuiz(question, sectionId);
    }
@PostMapping("/with-propositions")
public ResponseEntity<?> addQuizWithPropositions(@RequestBody QuizSectionRequestDTO dto) {
    quizSectionService.addQuizWithPropositions(dto);
    return ResponseEntity.status( 200).build(); // pas besoin de la variable
}

@PutMapping("/{id}")
public ResponseEntity<QuizSection> updateQuiz(@PathVariable Long id, @RequestBody QuizSectionRequestDTO dto) {
    QuizSection updated = quizSectionService.updateQuiz(id, dto);
    return ResponseEntity.ok(updated);
}


    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizSectionService.deleteQuiz(id);
    }

    @GetMapping("/{id}")
    public QuizSection getQuizById(@PathVariable Long id) {
        return quizSectionService.getQuizById(id);
    }
}
