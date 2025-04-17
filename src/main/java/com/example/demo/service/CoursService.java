package com.example.demo.service;

import com.example.demo.entity.Cours;
import com.example.demo.entity.CoursProjection;
import com.example.demo.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }
    /* public List<Cours> getCoursByProjetId(Long projetId) {
        return coursRepository.findByProjetId(projetId);
    }*/
    
    public Cours getCoursById(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    public List<CoursProjection> getCoursSansProjet(Long projetId) {
        return coursRepository.findCoursSansProjet(projetId);
    }

    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }
}
