package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SuperAdmin;
import com.example.demo.service.SuperAdminService;
import com.example.demo.repository.SuperAdminRepository;

@RestController
@RequestMapping("/api/superadmins")
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private SuperAdminRepository superAdminRepository;

    @GetMapping
    public List<SuperAdmin> getAll() {
        return superAdminService.findAll();
    }
    @GetMapping("/email")
    public SuperAdmin getByEmail(@RequestParam String email) {
        return superAdminRepository.findByEmail(email).orElse(null);
    }

    @PostMapping
    public SuperAdmin create(@RequestBody SuperAdmin superAdmin) {
        return superAdminService.save(superAdmin);
    }
}

