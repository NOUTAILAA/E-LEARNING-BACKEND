package com.example.demo.service;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Compteur;
import com.example.demo.entity.CompteurRequestDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.QuizSection;
import com.example.demo.entity.Section;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.CompteurRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.QuizSectionRepository;
import com.example.demo.repository.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteurService {

    @Autowired
    private CompteurRepository compteurRepository;
@Autowired
    private ApprenantRepository apprenantRepository;
@Autowired
    private QuizRepository quizChapitreRepository;
@Autowired
    private QuizSectionRepository quizSectionRepository;
@Autowired
    private SectionRepository sectionRepository;

    public List<Compteur> getAllCompteurs() {
        return compteurRepository.findAll();
    }

    public Compteur getCompteurById(Long id) {
        return compteurRepository.findById(id).orElse(null);
    }

    public Compteur saveCompteur(Compteur compteur) {
        return compteurRepository.save(compteur);
    }

    public void deleteCompteur(Long id) {
        compteurRepository.deleteById(id);
    }
public Compteur saveFromDTO(CompteurRequestDTO dto) {
    Apprenant apprenant = apprenantRepository.findById(dto.getApprenantId()).orElse(null);
    Section section = sectionRepository.findById(dto.getSectionId()).orElse(null);
    QuizSection quizSection = dto.getQuizSectionId() != null ? 
        quizSectionRepository.findById(dto.getQuizSectionId()).orElse(null) : null;
    Quiz quizChapitre = dto.getQuizChapitreId() != null ? 
        quizChapitreRepository.findById(dto.getQuizChapitreId()).orElse(null) : null;

    Compteur compteur = new Compteur();
    compteur.setApprenant(apprenant);
    compteur.setSection(section);
    compteur.setQuizSection(quizSection);
    compteur.setQuizChapitre(quizChapitre);
    compteur.setTempsPasse(dto.getTempsPasse());

    return compteurRepository.save(compteur);
}


}
