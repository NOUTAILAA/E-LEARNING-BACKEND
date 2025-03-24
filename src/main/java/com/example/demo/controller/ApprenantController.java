package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Apprenant;
import com.example.demo.service.ApprenantService;

@RestController
@RequestMapping("/api/apprenants")
public class ApprenantController {
    @Autowired
    private ApprenantService apprenantService;

    @GetMapping
    public List<Apprenant> getAll() {
        return apprenantService.findAll();
    }

    @PostMapping
    public Apprenant create(@RequestBody Apprenant apprenant) {
        return apprenantService.save(apprenant);
    }
}
