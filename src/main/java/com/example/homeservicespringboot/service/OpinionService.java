package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Opinion;
import com.example.homeservicespringboot.entity.capability.Suggestion;

public interface OpinionService {
    boolean createOpinion(Opinion opinion);
}
