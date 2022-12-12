package com.example.homeservicespringboot.repository;

import com.example.homeservicespringboot.entity.capability.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
