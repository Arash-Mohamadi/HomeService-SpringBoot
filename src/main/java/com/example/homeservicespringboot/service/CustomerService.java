package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Opinion;
import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Customer;

import java.util.List;


public interface CustomerService {

    boolean signup(Customer customer);

    Customer editPassword(String password);

    boolean registerOrder(Order order, String subServiceName);

    boolean registerOpinion(Opinion opinion, Long orderId);

    boolean selectSpecialist();

    List<Suggestion> showAllSuggestion();



}
