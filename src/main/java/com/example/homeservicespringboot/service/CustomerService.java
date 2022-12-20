package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Opinion;
import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.users.Customer;

import java.util.List;


public interface CustomerService {

    boolean signup(Customer customer);

    Customer editPassword(String username ,String password);

    boolean registerOrder(Order order, String subServiceName,String customerUsername);

    boolean registerOpinion(Opinion opinion, Long orderId);

    boolean selectSpecialist(Long suggestionId,Long orderId);

    List<Suggestion> showAllSuggestionWithPrice(Long orderId);
    List<Suggestion> showAllSuggestionWithScore(Long orderId);

    boolean changeStatusOrderToStarted(Long orderId);



}
