package com.example.demo.service;

import com.example.demo.entity.Proposition;
import com.example.demo.entity.PropositionProjection;
import com.example.demo.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropositionService {

    @Autowired
    private PropositionRepository propositionRepository;

    public List<PropositionProjection> getLightByQuizId(Long quizId) {
    return propositionRepository.findLightByQuizId(quizId);
}


    public Proposition save(Proposition proposition) {
        return propositionRepository.save(proposition);
    }

    public void delete(Long id) {
        propositionRepository.deleteById(id);
    }
}
