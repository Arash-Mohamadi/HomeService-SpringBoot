package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Credit;
import com.example.homeservicespringboot.repository.CreditRepository;
import com.example.homeservicespringboot.service.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository ;

    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    @Transactional
    public boolean createCredit(Credit credit) {
         creditRepository.save(credit);
         return true ;

    }
}
