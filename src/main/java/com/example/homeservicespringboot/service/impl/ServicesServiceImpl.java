package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.repository.ServiceRepository;
import com.example.homeservicespringboot.service.ServicesService;
import org.springframework.stereotype.Service;

@Service
public class ServicesServiceImpl implements ServicesService {
    private ServiceRepository serviceRepository;
    // constructor


    @Override
    public Services fetchServiceWithName(String name) {
        return null;
    }

}
