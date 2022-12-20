package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.SubServices;
import com.example.homeservicespringboot.repository.SubServiceRepository;
import com.example.homeservicespringboot.service.SubServicesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SubServicesServiceImpl implements SubServicesService {
    private SubServiceRepository subServiceRepository ;
    // constructor
    public SubServicesServiceImpl(SubServiceRepository subServiceRepository) {
        this.subServiceRepository = subServiceRepository;
    }

    @Override
    public Optional<SubServices> fetchSubServiceWithName(String name) {
        return subServiceRepository.findSubServicesByName(name);
    }

    @Override
    public boolean createSubService(SubServices subServices) {
        subServiceRepository.save(subServices);
        return true;
    }

    @Override
    @Transactional
    public void removeOfService(String subServicesName, Long serviceId) {
      //  subServiceRepository.removeOfService(subServicesName,serviceId);
    }

    @Override
    public void removeOfServiceById(Long subId) {
        subServiceRepository.deleteById(subId);
    }
}
