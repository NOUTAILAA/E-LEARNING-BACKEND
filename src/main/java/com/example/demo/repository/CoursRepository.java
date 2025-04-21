package com.example.demo.repository;

import com.example.demo.entity.Cours;
import com.example.demo.entity.CoursProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    @Query("SELECT c.id as id, c.titre as titre, c.description as description, " +
    "c.tempsEstimer as tempsEstimer, c.dateCreation as dateCreation, " +
    "c.dateMAJ as dateMAJ, c.projet.nom as projetNom " +
    "FROM Cours c WHERE c.projet.id = :projetId" +
    " ORDER BY c.id ASC"
    )
List<CoursProjection> findCoursSansProjet(@Param("projetId") Long projetId);
//List<Cours> findByProjetId(Long projetId);

}
