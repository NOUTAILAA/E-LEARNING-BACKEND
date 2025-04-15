package com.example.demo.controller;

import com.example.demo.entity.Chapitre;
import com.example.demo.service.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapitres")
public class ChapitreController {

    @Autowired
    private ChapitreService chapitreService;

    @GetMapping
    public List<Chapitre> getAllChapitres() {
        return chapitreService.getAllChapitres();
    }

    @GetMapping("/{id}")
    public Chapitre getChapitreById(@PathVariable Long id) {
        return chapitreService.getChapitreById(id);
    }

    @GetMapping("/cours/{coursId}")
    public List<Chapitre> getChapitresByCoursId(@PathVariable Long coursId) {
        return chapitreService.getChapitresByCoursId(coursId);
    }

    @PostMapping
    public Chapitre createChapitre(@RequestBody Chapitre chapitre) {
        return chapitreService.saveChapitre(chapitre);
    }

    @DeleteMapping("/{id}")
    public void deleteChapitre(@PathVariable Long id) {
        chapitreService.deleteChapitre(id);
    }
}
