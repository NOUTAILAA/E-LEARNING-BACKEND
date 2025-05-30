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
Section section = null;
if (dto.getSectionId() != null) {
    section = sectionRepository.findById(dto.getSectionId())
        .orElseThrow(() -> new IllegalArgumentException("Section introuvable avec ID " + dto.getSectionId()));
}
QuizSection quizSection = null;
if (dto.getQuizSectionId() != null) {
    quizSection = quizSectionRepository.findById(dto.getQuizSectionId())
        .orElseThrow(() -> new IllegalArgumentException("QuizSection introuvable avec ID " + dto.getQuizSectionId()));
}

    Quiz quizChapitre = null;
if (dto.getQuizChapitreId() != null) {
    quizChapitre = quizChapitreRepository.findById(dto.getQuizChapitreId())
        .orElseThrow(() -> new IllegalArgumentException("QuizChapitre introuvable avec ID " + dto.getQuizChapitreId()));
}


    Compteur compteur = new Compteur();
    compteur.setApprenant(apprenant);
    compteur.setSection(section);
    compteur.setQuizSection(quizSection);
    compteur.setQuizChapitre(quizChapitre);
    compteur.setTempsPasse(dto.getTempsPasse());

    return compteurRepository.save(compteur);
}


}
