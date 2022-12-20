package com.example.homeservicespringboot.service;



import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Specialist;

import java.io.File;
import java.util.Optional;

public interface SpecialistService {

    boolean signup(Specialist specialist, File file);

    Specialist editPassword(String username ,String password);
    boolean sendSuggestion(Suggestion suggestion,Long orderId, String specialistUsername);

    Optional<Specialist> fetchSpecialistWithUsername(String username);

    Specialist createSpecialist(Specialist specialist);
}
