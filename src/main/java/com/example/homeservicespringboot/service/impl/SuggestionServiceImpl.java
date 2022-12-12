package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.repository.SubServiceRepository;
import com.example.homeservicespringboot.service.SuggestionService;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private SubServiceRepository subServiceRepository ;
    // constructor


    @Override
    public boolean createSuggestion(Suggestion suggestion) {
        return false;
    }

}
