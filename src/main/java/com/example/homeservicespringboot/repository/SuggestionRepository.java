package com.example.homeservicespringboot.repository;

import com.example.homeservicespringboot.entity.capability.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {

    @Query("select s from Suggestion s where s.order.id=:id order by s.price desc ")
    List<Suggestion> findAllSuggestionOneOrderByPrice(Long id);

    @Query("select s from Suggestion s where s.order.id=:id order by s.specialist.scoreAvg asc ")
    List<Suggestion> findAllSuggestionOneOrderBySpecialistScore(Long id);
}
