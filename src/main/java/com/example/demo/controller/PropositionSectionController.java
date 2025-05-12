package com.example.demo.controller;

import com.example.demo.entity.PropositionSection;
import com.example.demo.entity.PropositionSectionDTO;
import com.example.demo.service.PropositionSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propositionsection")
@CrossOrigin(origins = "*")
public class PropositionSectionController {

    @Autowired
    private PropositionSectionService propositionSectionService;

   

    @PostMapping
    public PropositionSection addProposition(
            @RequestParam String reponse,
            @RequestParam boolean correcte,
            @RequestParam Long quizId
    ) {
        return propositionSectionService.addProposition(reponse, correcte, quizId);
    }

    @DeleteMapping("/{id}")
    public void deleteProposition(@PathVariable Long id) {
        propositionSectionService.deleteProposition(id);
    }
    // PropositionSectionController.java
@GetMapping("/quiz/{quizId}")
public List<PropositionSectionDTO> getPropositionsByQuiz(@PathVariable Long quizId) {
    return propositionSectionService.getPropositionsByQuizId(quizId);
}
@PutMapping("/{id}")
public PropositionSection updateProposition(
        @PathVariable Long id,
        @RequestBody PropositionSectionDTO updatedProposition
) {
    return propositionSectionService.updateProposition(id, updatedProposition);
}



}
