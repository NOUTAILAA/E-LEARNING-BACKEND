package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Manager;
import com.example.demo.service.ManagerService;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<Manager> getAll() {
        return managerService.findAll();
    }

    @PostMapping
    public Manager create(@RequestBody Manager manager) {
        return managerService.save(manager);
    }
}
