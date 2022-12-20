package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Credit;
import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.capability.SubServices;
import com.example.homeservicespringboot.entity.enums.SpecialistStatus;
import com.example.homeservicespringboot.entity.users.Manager;
import com.example.homeservicespringboot.entity.users.Specialist;
import com.example.homeservicespringboot.exception.*;
import com.example.homeservicespringboot.repository.ManagerRepository;
import com.example.homeservicespringboot.service.*;
import com.example.homeservicespringboot.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;
    private ServicesService servicesService;
    private SubServicesService subServicesService;
    private SpecialistService specialistService;
    private CreditService creditService;

    // constructor


    public ManagerServiceImpl(ManagerRepository managerRepository,
                              ServicesService servicesService,
                              SubServicesService subServicesService,
                              SpecialistService specialistService,
                              CreditService creditService) {
        this.managerRepository = managerRepository;
        this.servicesService = servicesService;
        this.subServicesService = subServicesService;
        this.specialistService = specialistService;
        this.creditService = creditService;
    }

    @Override
    @Transactional
    public boolean addService(Services services) {
        try {
            Validation.checkEntity(services);
            checkServices(services);
            return true;
        } catch (CustomizedIllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    @Transactional
    public boolean addSubServicesToServices(String serviceName, SubServices subServices) {

        try {
            Services services = servicesService.fetchServiceWithName(serviceName).get();
            Validation.checkEntity(subServices);
            checkSubService(subServices, services);
            return true;

        } catch (CustomizedDuplicateService |
                 CustomizedIllegalArgumentException |
                 CustomizesDuplicateSubService e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    @Override
    @Transactional
    public boolean removeSubServicesOfServices( String subServiceName) {
        try {
            removeSubServiceByManager( subServiceName);
            return true;
        } catch (CustomizedNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    @Transactional
    public boolean addSpecialistToSubServices(String subServiceName, String specialistUsername) {
        try {
            addSpecialistByManager(subServiceName, specialistUsername);
            return true;
        } catch (CustomizedNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (CustomizedInvalidStatusException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    @Transactional
    public boolean removeSpecialistOfSubServices(String subServiceName, String specialistUsername) {
        try {

            removeSpecialistByManager(subServiceName, specialistUsername);
            return true;
        } catch (CustomizedNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (CustomizedInvalidStatusException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
   // @Transactional(rollbackFor = CustomizedNotFoundException.class)
    public Manager editPassword(String username, String password) {
        Manager manager = null;
        // validate password
        manager = managerRepository.findManagerByUsername(username).orElseThrow(
                () -> new CustomizedNotFoundException("desired manager not found ."));

        try {
            manager.setPassword(password);
            managerRepository.save(manager); //update
            return manager;
        } catch (Exception e) {
           System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean confirmSpecialist(String specialistUsername) {
        try {
            checkSpecialist(specialistUsername);
            return true;
        } catch (CustomizedNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean editSubServices(String subServiceName, SubServices subServices) {
        try {
            SubServices subServicesFound = checkSubServiceWithName(subServiceName);
            replaceSubService(subServicesFound, subServices);
            return true;
        } catch (CustomizesDuplicateSubService e) {
            System.err.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }


    }

    @Override
    public List<Services> showAllService() {
        return
                servicesService.showAllService();
    }



    // helper methods
    void checkServices(Services services) {
        Optional<Services> service = servicesService.fetchServiceWithName(services.getName());
        if (service.isEmpty()) {
            servicesService.createService(services);
        } else
            throw new CustomizedDuplicateService("this service available.");
    }

    Services checkServices(String name) {
        Optional<Services> service = servicesService.fetchServiceWithName(name);
        if (service.isPresent()) {
            return service.get();
        } else
            throw new CustomizedDuplicateService("this service available.");
    }

    void checkSubService(SubServices subServices, Services services) {
        Optional<SubServices> name = subServicesService.fetchSubServiceWithName(subServices.getName());

        if (name.isEmpty()) {
            services.addSubServices(subServices);
            subServicesService.createSubService(subServices); //save
            servicesService.createService(services); // update

        } else
            throw new CustomizesDuplicateSubService("this subService available.");
    }

    boolean checkSubService(String name) {
        Optional<SubServices> subService = subServicesService.fetchSubServiceWithName(name);
        if (subService.isEmpty()) {
            return true;
        } else
            throw new CustomizesDuplicateSubService("this subService available.");
    }

    SubServices checkSubServiceWithName(String name) {
        Optional<SubServices> subService = subServicesService.fetchSubServiceWithName(name);
        if (subService.isPresent()) {
            return subService.get();
        } else
            throw new CustomizesDuplicateSubService("this subService available.");
    }

    void replaceSubService(SubServices subServicesFound, SubServices subServices) {
        if (checkSubService(subServices.getName())) {
            subServicesFound.setName(subServices.getName());
            subServicesFound.setBasePrice(subServices.getBasePrice());
            subServicesService.createSubService(subServicesFound);
        }
    }

    void checkSpecialist(String username) {
        Specialist specialistFound = specialistService.fetchSpecialistWithUsername(username).orElseThrow(
                () -> new CustomizedNotFoundException("specialist not found"));
        specialistFound.setStatus(SpecialistStatus.CONFIRMED);
        Credit credit = new Credit();
        specialistFound.setCredit(credit);
        creditService.createCredit(credit);
        specialistService.createSpecialist(specialistFound);
    }

    @Transactional
    void addSpecialistByManager(String subServiceName, String specialistUsername) {

        SubServices subServicesFound = subServicesService.fetchSubServiceWithName(subServiceName).orElseThrow(
                () -> new CustomizedNotFoundException("this subService notfound"));

        Specialist specialistFound = specialistService.fetchSpecialistWithUsername(specialistUsername).orElseThrow(
                () -> new CustomizedNotFoundException("this specialist notfound"));

        if (specialistFound.getStatus() == SpecialistStatus.CONFIRMED) {
          //  subServicesFound.addSpecialist(specialistFound);
            specialistFound.addSubService(subServicesFound);
            specialistService.createSpecialist(specialistFound); // update
            subServicesService.createSubService(subServicesFound); // update
        } else
            throw new CustomizedInvalidStatusException("specialist should CONFIRMED");
    }

    void removeSpecialistByManager(String subServiceName, String specialistUsername) {

        SubServices subServicesFound = subServicesService.fetchSubServiceWithName(subServiceName).orElseThrow(
                () -> new CustomizedNotFoundException("this subService notfound"));

        Specialist specialistFound = specialistService.fetchSpecialistWithUsername(specialistUsername).orElseThrow(
                () -> new CustomizedNotFoundException("this specialist notfound"));

        if (specialistFound.getStatus() == SpecialistStatus.CONFIRMED) {
            specialistFound.getSubServicesSet().remove(subServicesFound);
            specialistService.createSpecialist(specialistFound);
        } else
            throw new CustomizedInvalidStatusException("specialist should CONFIRMED");
    }


    void removeSubServiceByManager( String subServiceName) {

        SubServices subServicesFound = subServicesService.fetchSubServiceWithName(subServiceName).orElseThrow(
                () -> new CustomizedNotFoundException("this subService notfound"));

        subServicesService.removeOfServiceById(subServicesFound.getId());


       // subServicesService.removeOfService(subServicesFound.getName(), serviceFound.getId());
    }
}

