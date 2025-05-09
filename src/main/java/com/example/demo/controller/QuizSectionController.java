package com.example.demo.controller;

import com.example.demo.entity.QuizSection;
import com.example.demo.entity.QuizSectionDTO;
import com.example.demo.service.QuizSectionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizSectionService.deleteQuiz(id);
    }

    @GetMapping("/{id}")
    public QuizSection getQuizById(@PathVariable Long id) {
        return quizSectionService.getQuizById(id);
    }
}
