package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Suggestion;

public interface SuggestionService {
    boolean createSuggestion(Suggestion suggestion);
}
