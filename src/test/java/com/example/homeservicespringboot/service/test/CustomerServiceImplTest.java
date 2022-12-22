package com.example.homeservicespringboot.service.test;

import com.example.homeservicespringboot.entity.capability.Opinion;
import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Customer;
import com.example.homeservicespringboot.entity.users.Manager;
import com.example.homeservicespringboot.service.CustomerService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;



    @Test
    void signup() {
        Customer customer = new Customer("jafar", "kiya", "llkdkjdf0@email",
                "jdjhdh", "123456789");
        boolean bool = customerService.signup(customer);
        assertTrue(bool);

    }

    @Test
    void editPassword() {
        Customer customer = customerService.editPassword("jafar", "111111111");
        String newPass = "111111111";
        assertEquals(customer.getPassword(),newPass);
    }

    @Test
    void registerOrder() {
        Order order = new Order(12, LocalTime.of(13,30),
                LocalDate.of(2022,12,18),"hhhihvg","kjvisgvisg");
        boolean bool = customerService.registerOrder(order, "window","jafar");
        assertTrue(bool);
    }

    @Test
    void registerOpinion() {
        Opinion opinion = new Opinion(10);
        boolean bool = customerService.registerOpinion(opinion, 5L);
        assertTrue(bool);
    }

    @Test
    void selectSpecialist() {
        boolean bool = customerService.selectSpecialist(3L, 4L);
        assertTrue(bool);
    }

    @Test
    void changeStatusOrderToStarted() {
        boolean bool = customerService.changeStatusOrderToStarted(6L);
        assertTrue(bool);
    }

    @Test
    void showAllSuggestionWithPrice() {
        List<Suggestion> suggestions = customerService.showAllSuggestionWithPrice(4L);
        assertEquals(suggestions.size(),1);

    }

    @Test
    void showAllSuggestionWithScore() {

        List<Suggestion> suggestions = customerService.showAllSuggestionWithScore(4L);
        assertEquals(suggestions.size(),1);
    }
}