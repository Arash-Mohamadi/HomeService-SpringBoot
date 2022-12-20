package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.capability.SubServices;

import java.util.Optional;

public interface SubServicesService {
   Optional<SubServices> fetchSubServiceWithName(String name);
   boolean createSubService(SubServices subServices);
   void removeOfService(String subServicesName, Long serviceId);
   void removeOfServiceById(Long subId);
}
