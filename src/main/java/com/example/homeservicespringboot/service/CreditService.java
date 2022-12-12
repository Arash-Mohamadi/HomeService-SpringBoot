package com.example.homeservicespringboot.service;

import com.example.homeservicespringboot.entity.capability.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditService  {

    boolean createCredit(Credit credit);
}
