package com.example.homeservicespringboot.service;



import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Specialist;

public interface SpecialistService {

    Specialist signup(Specialist specialist);

    Specialist editPassword(String password);
    boolean sendSuggestion(Suggestion suggestion,Long OrderId,Specialist specialist);

    Specialist fetchSpecialistWithUsername(String username);
}
