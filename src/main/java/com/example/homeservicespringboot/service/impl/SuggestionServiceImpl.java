package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.repository.SuggestionRepository;
import com.example.homeservicespringboot.service.SuggestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {
    private SuggestionRepository suggestionRepository;

    // constructor
    public SuggestionServiceImpl(SuggestionRepository suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public boolean createSuggestion(Suggestion suggestion) {
        try {
            suggestionRepository.save(suggestion);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Suggestion> findSuggestionWithPrice(Long id) {

        return suggestionRepository.findAllSuggestionOneOrderByPrice(id);

    }

    @Override
    public List<Suggestion> findSuggestionWithSpecialistScore(Long id) {

        return suggestionRepository.findAllSuggestionOneOrderBySpecialistScore(id);
    }

    @Override
    public Optional<Suggestion> findSuggestionWithId(Long suggestionId) {
        return suggestionRepository.findById(suggestionId);
    }

}
