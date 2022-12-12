package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.capability.SubServices;
import com.example.homeservicespringboot.entity.users.Manager;


import java.util.List;


public interface ManagerService {

    boolean addService(Services services);

    boolean addSubServicesToServices(String serviceName, SubServices subServices);

    boolean removeSubServicesOfServices(String serviceName, String subServiceName);

    boolean addSpecialistToSubServices(String subServiceName, String specialistUsername);

    boolean removeSpecialistOfSubServices(String subServiceName, String specialistUsername);

    Manager editPassword(String password);

    boolean confirmSpecialist(String specialistUsername);

    boolean editSubServices(String subServiceName, SubServices subServices);

    List<Services> showAllService();
}
