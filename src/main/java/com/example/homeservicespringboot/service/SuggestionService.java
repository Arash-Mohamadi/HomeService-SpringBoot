package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Suggestion;

import java.util.List;
import java.util.Optional;

public interface SuggestionService {
    boolean createSuggestion(Suggestion suggestion);

    List<Suggestion> findSuggestionWithPrice(Long id);
    List<Suggestion> findSuggestionWithSpecialistScore(Long id);

    Optional<Suggestion> findSuggestionWithId(Long suggestionId);
}
