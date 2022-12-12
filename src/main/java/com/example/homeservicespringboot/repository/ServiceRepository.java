package com.example.homeservicespringboot.repository;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Long> {

    Optional<Services> findServicesByName(String name);
}
