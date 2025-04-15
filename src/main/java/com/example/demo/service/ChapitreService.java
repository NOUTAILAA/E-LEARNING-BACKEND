package com.example.demo.service;

import com.example.demo.entity.Chapitre;
import com.example.demo.repository.ChapitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapitreService {

    @Autowired
    private ChapitreRepository chapitreRepository;

    public List<Chapitre> getAllChapitres() {
        return chapitreRepository.findAll();
    }

    public Chapitre getChapitreById(Long id) {
        return chapitreRepository.findById(id).orElse(null);
    }

    public List<Chapitre> getChapitresByCoursId(Long coursId) {
        return chapitreRepository.findByCoursId(coursId);
    }

    public Chapitre saveChapitre(Chapitre chapitre) {
        return chapitreRepository.save(chapitre);
    }

    public void deleteChapitre(Long id) {
        chapitreRepository.deleteById(id);
    }
}
