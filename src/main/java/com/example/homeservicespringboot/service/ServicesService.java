package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Services;

import java.util.List;
import java.util.Optional;


public interface ServicesService {
    Optional<Services> fetchServiceWithName(String name);
    Boolean createService(Services services);

    List<Services> showAllService();

    Long count();
}
