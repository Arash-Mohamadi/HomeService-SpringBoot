package com.example.homeservicespringboot.repository;

import com.example.homeservicespringboot.entity.capability.Services;
import com.example.homeservicespringboot.entity.capability.SubServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubServices, Long> {

    Optional<SubServices> findSubServicesByName(String name);

//    @Transactional
//    @Modifying
//    @Query("delete from SubServices sub where sub.name=:subServicesName and sub.services.id=:id")
//    void removeOfService(String subServicesName, Long serviceId);
}
