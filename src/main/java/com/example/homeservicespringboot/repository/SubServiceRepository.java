package com.example.homeservicespringboot.repository;

import com.example.homeservicespringboot.entity.capability.SubServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubServices,Long> {

    Optional<SubServices> findSubServicesByName(String name);
}
