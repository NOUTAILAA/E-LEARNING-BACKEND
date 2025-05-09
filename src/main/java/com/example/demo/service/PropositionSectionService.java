package com.example.demo.service;

import com.example.demo.entity.PropositionSection;
import com.example.demo.entity.PropositionSectionDTO;
import com.example.demo.entity.QuizSection;
import com.example.demo.repository.PropositionSectionRepository;
import com.example.demo.repository.QuizSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropositionSectionService {

    @Autowired
    private PropositionSectionRepository propositionSectionRepository;

    @Autowired
    private QuizSectionRepository quizSectionRepository;



    public PropositionSection addProposition(String reponse, boolean correcte, Long quizId) {
        Optional<QuizSection> quizOpt = quizSectionRepository.findById(quizId);
        if (quizOpt.isEmpty()) {
            throw new RuntimeException("Quiz introuvable avec l'ID : " + quizId);
        }

        PropositionSection prop = new PropositionSection();
        prop.setReponse(reponse);
        prop.setCorrecte(correcte);
        prop.setQuiz(quizOpt.get());
        return propositionSectionRepository.save(prop);
    }

    public boolean deleteProposition(Long id) {
        if (propositionSectionRepository.existsById(id)) {
            propositionSectionRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public PropositionSection save(PropositionSection prop) {
        return propositionSectionRepository.save(prop);
    }

    public List<PropositionSection> getByQuizId(Long quizId) {
        return propositionSectionRepository.findByQuizId(quizId);
    }
public List<PropositionSectionDTO> getPropositionsByQuizId(Long quizId) {
    return propositionSectionRepository.findDTOByQuizId(quizId);
}

    public PropositionSection getById(Long id) {
        return propositionSectionRepository.findById(id).orElse(null);  // ✅ ajout de cette méthode
    }
}
