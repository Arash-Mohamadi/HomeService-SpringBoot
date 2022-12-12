package com.example.homeservicespringboot.repository;


import com.example.homeservicespringboot.entity.users.Customer;
import com.example.homeservicespringboot.entity.users.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist,Long> {
    Optional<Specialist> findSpecialistByUsername(String username);
}
