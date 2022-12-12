package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.SubServices;
import com.example.homeservicespringboot.repository.SubServiceRepository;
import com.example.homeservicespringboot.service.SubServicesService;
import org.springframework.stereotype.Service;

@Service
public class SubServicesServiceImpl implements SubServicesService {
    private SubServiceRepository subServiceRepository ;
    // constructor


    @Override
    public SubServices fetchSubServiceWithName(String name) {
        return null;
    }
}
