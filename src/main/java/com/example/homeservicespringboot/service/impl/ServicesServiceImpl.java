package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.repository.ServiceRepository;
import com.example.homeservicespringboot.service.ServicesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesServiceImpl implements ServicesService {
    private ServiceRepository serviceRepository;

    // constructor
    public ServicesServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Optional<Services> fetchServiceWithName(String name) {

        return serviceRepository.findServicesByName(name);
    }

    @Override
    public Boolean createService(Services services) {
        serviceRepository.save(services);
        return true ;
    }

    @Override
    public List<Services> showAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public Long count() {
        return serviceRepository.count();
    }

}
