package com.example.homeservicespringboot.service.test;

import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Specialist;
import com.example.homeservicespringboot.service.SpecialistService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpecialistServiceImplTest {

    @Autowired
    private SpecialistService specialistService ;

    @Test
    void signup() {
        Specialist specialist = new Specialist("arash","moh","ali@gmail"
                ,"ali01","Arash1234");
        File file = new File("src/main/image.jpg");
        boolean bool = specialistService.signup(specialist, file);
        assertTrue(bool);
    }

    @Test
    void editPassword() {
        Specialist specialist = specialistService.editPassword("ali01", "12345696");
        String newPass = "12345696";
        assertEquals(specialist.getPassword(),newPass);
    }

    @Test
    void sendSuggestion() {

        Suggestion suggestion = new Suggestion(15,2);
        boolean bool = specialistService.sendSuggestion(suggestion, 3L, "ali01");
        assertTrue(bool);

    }




}