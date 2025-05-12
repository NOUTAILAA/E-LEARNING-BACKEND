package com.example.demo.service;

import com.example.demo.entity.PropositionSection;
import com.example.demo.entity.QuizSection;
import com.example.demo.entity.QuizSectionDTO;
import com.example.demo.entity.QuizSectionRequestDTO;
import com.example.demo.entity.Section;
import com.example.demo.repository.QuizSectionRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizSectionService {

    @Autowired
    private QuizSectionRepository quizSectionRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public List<QuizSection> getQuizzesBySectionId(Long sectionId) {
        return quizSectionRepository.findBySectionId(sectionId);
    }

    public QuizSection getQuizById(Long id) {
        return quizSectionRepository.findById(id).orElse(null);
    }

    public QuizSection addQuiz(String question, Long sectionId) {
        Optional<Section> sectionOpt = sectionRepository.findById(sectionId);
        if (sectionOpt.isEmpty()) {
            throw new RuntimeException("Section introuvable avec l'ID : " + sectionId);
        }

        QuizSection quiz = new QuizSection();
        quiz.setQuestion(question);
        quiz.setSection(sectionOpt.get());
        return quizSectionRepository.save(quiz);
    }

    public boolean deleteQuiz(Long id) {
        if (quizSectionRepository.existsById(id)) {
            quizSectionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public QuizSection save(QuizSection quizSection) {
        return quizSectionRepository.save(quizSection);
    }

    public List<QuizSection> getBySectionId(Long sectionId) {
        return quizSectionRepository.findBySectionId(sectionId);
    }

    public QuizSection getById(Long id) {
        return quizSectionRepository.findById(id).orElse(null);  // ✅ ajout de cette méthode
    }
    public List<QuizSectionDTO> getQuizDTOBySectionId(Long sectionId) {
    return quizSectionRepository.findQuizDTOBySectionId(sectionId);
}

public QuizSection addQuizWithPropositions(QuizSectionRequestDTO dto) {
    Section section = sectionRepository.findById(dto.getSectionId())
        .orElseThrow(() -> new RuntimeException("Section introuvable"));

    QuizSection quiz = new QuizSection();
    quiz.setQuestion(dto.getQuestion());
    quiz.setSection(section);

    List<PropositionSection> props = dto.getPropositions().stream()
        .map(p -> {
            PropositionSection prop = new PropositionSection();
            prop.setReponse(p.getReponse());
            prop.setCorrecte(p.isCorrecte());
            prop.setQuiz(quiz);
            return prop;
        }).toList();

    quiz.setPropositions(props);

    return quizSectionRepository.save(quiz); // cascade persist automatiquement les propositions
}

}
