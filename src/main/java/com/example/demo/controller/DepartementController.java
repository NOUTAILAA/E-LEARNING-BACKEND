package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Departement;
import com.example.demo.service.DepartementService;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {
    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Departement> getAll() {
        return departementService.findAll();
    }

    @PostMapping
    public Departement create(@RequestBody Departement departement) {
        return departementService.save(departement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getById(@PathVariable Long id) {
        return departementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departementService.delete(id);
    }
}
