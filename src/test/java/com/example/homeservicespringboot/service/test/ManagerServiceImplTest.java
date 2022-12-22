package com.example.homeservicespringboot.service.test;


import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.capability.SubServices;
import com.example.homeservicespringboot.entity.users.Manager;
import com.example.homeservicespringboot.service.ManagerService;
import static  org.junit.jupiter.api.Assertions.*;
import com.example.homeservicespringboot.service.ServicesService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerServiceImplTest {
    @Autowired
    private ManagerService managerService ;
    @Autowired
    private ServicesService servicesService;

    @Test
    void addService() {
        Services services = new Services("hhhhhh");
        boolean bool = managerService.addService(services);
        assertTrue(bool);
    }

    @Test
    void addSubServicesToServices() {
        SubServices subServices = new SubServices(10,"tool for home","hydhtev");
        boolean bool = managerService.addSubServicesToServices("building", subServices);
        assertTrue(bool);
    }

    @Test
    void removeSubServicesOfServices() {
        boolean bool = managerService.removeSubServicesOfServices( "ghfshhe");
        assertTrue(bool);
    }

    @Test
    void addSpecialistToSubServices() {
        boolean bool = managerService.addSpecialistToSubServices("window", "safdar01");
        assertTrue(bool);
    }

    @Test
    void removeSpecialistOfSubServices() {

        boolean bool = managerService.removeSpecialistOfSubServices("window", "gaf");
        assertTrue(bool);


    }

    @Test
    void editPassword() {
        Manager manager = managerService.editPassword("arash", "111111111");
        String newPass = "111111111";
        assertEquals(manager.getPassword(),newPass);
    }

    @Test
    void confirmSpecialist() {
        boolean bool = managerService.confirmSpecialist("ali01");
        assertTrue(bool);
    }

    @Test
    void editSubServices() {
        SubServices subServices = new SubServices(11,"tool for building","door");
        boolean bool = managerService.editSubServices("roof", subServices);
        assertTrue(bool);
    }

    @Test
    void showAllService() {
        List<Services> services = managerService.showAllService();
        assertEquals(services.size(),servicesService.count());
    }
}