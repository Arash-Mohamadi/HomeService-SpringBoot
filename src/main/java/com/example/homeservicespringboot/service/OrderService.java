package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;

import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);
    Optional<Order> fetchOrderById(Long orderId);
}
