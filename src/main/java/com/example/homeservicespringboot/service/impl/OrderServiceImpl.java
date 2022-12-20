package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.repository.OrderRepository;
import com.example.homeservicespringboot.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    // constructor


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Optional<Order> fetchOrderById(Long orderId) {

        return orderRepository.findById(orderId);
    }


}
