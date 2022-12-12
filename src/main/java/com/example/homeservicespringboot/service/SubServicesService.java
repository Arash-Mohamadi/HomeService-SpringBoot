package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.SubServices;

public interface SubServicesService {
   SubServices fetchSubServiceWithName(String name);
}
