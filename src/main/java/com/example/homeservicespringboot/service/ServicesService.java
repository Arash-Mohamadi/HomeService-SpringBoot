package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Services;


public interface ServicesService {
    Services fetchServiceWithName(String name);
}
