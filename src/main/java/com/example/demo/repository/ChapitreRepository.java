package com.example.demo.repository;

import com.example.demo.entity.Chapitre;
import com.example.demo.entity.ChapitreProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {

    // ✅ Projection personnalisée
    @Query("SELECT c.id AS id, c.titre AS titre, c.tempsEstimer AS tempsEstimer, " +
           "CAST(c.dateCreation AS string) AS dateCreation, " +
           "CAST(c.dateMAJ AS string) AS dateMAJ " +
           "FROM Chapitre c WHERE c.cours.id = :coursId" +
           " ORDER BY c.id ASC")
    List<ChapitreProjection> findLightByCoursId(@Param("coursId") Long coursId);
}
