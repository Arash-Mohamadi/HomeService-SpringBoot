package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;

public interface OrderService {

    boolean createOrder(Order order);
}
