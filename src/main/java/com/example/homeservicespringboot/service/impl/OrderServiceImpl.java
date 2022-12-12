package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.repository.OrderRepository;
import com.example.homeservicespringboot.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    // constructor


    @Override
    public boolean createOrder(Order order) {
        return false;
    }
}
