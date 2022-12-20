package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.Credit;
import com.example.homeservicespringboot.entity.capability.Order;
import com.example.homeservicespringboot.entity.capability.Suggestion;
import com.example.homeservicespringboot.entity.enums.OrderStatus;
import com.example.homeservicespringboot.entity.enums.SpecialistStatus;
import com.example.homeservicespringboot.entity.enums.UserType;
import com.example.homeservicespringboot.entity.users.Specialist;
import com.example.homeservicespringboot.exception.CustomizedFormatOrSizeFileException;
import com.example.homeservicespringboot.exception.CustomizedIllegalArgumentException;
import com.example.homeservicespringboot.exception.CustomizedNotFoundException;
import com.example.homeservicespringboot.repository.SpecialistRepository;
import com.example.homeservicespringboot.service.CreditService;
import com.example.homeservicespringboot.service.OrderService;
import com.example.homeservicespringboot.service.SpecialistService;
import com.example.homeservicespringboot.service.SuggestionService;
import com.example.homeservicespringboot.util.FileFormat;
import com.example.homeservicespringboot.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class SpecialistServiceImpl implements SpecialistService {

    private SpecialistRepository specialistRepository;
    private CreditService creditService;

    private OrderService orderService;

    private SuggestionService suggestionService ;

    // constructor
    public SpecialistServiceImpl(SpecialistRepository specialistRepository,
                                 CreditService creditService,
                                 OrderService orderService, SuggestionService suggestionService) {
        this.specialistRepository = specialistRepository;
        this.creditService = creditService;
        this.orderService = orderService;
        this.suggestionService = suggestionService;
    }

    @Override
    @Transactional
    public boolean signup(Specialist specialist, File file) {
        Specialist specialistFill;
        try {
            Validation.checkEntity(specialist);
            byte[] bytes = checkFile(file); // check file
            specialistFill = fillSpecialist(specialist); // fill specialist
            specialistFill.setPhoto(bytes); // set photo
            specialistRepository.save(specialistFill); // save
            return true;
        } catch (CustomizedIllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Specialist editPassword(String username, String password) {
        Specialist specialist = specialistRepository.findSpecialistByUsername(username).orElseThrow(
                () -> new CustomizedNotFoundException("desired specialist not found ."));
        try {
            specialist.setPassword(password);
            Validation.checkEntity(specialist);
            specialistRepository.save(specialist);
            return specialist;
        } catch (CustomizedIllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean sendSuggestion(Suggestion suggestion, Long orderId, String specialistUsername ) {
        Order order = null;
        Specialist specialist = specialistRepository.findSpecialistByUsername(specialistUsername).orElseThrow(
                () -> new CustomizedNotFoundException("desired order not found ."));
        if (specialist.getStatus() == SpecialistStatus.CONFIRMED) {
            order = orderService.fetchOrderById(orderId).orElseThrow(
                    () -> new CustomizedNotFoundException("desired order not found ."));

            if (order != null && (order.getStatus() == OrderStatus.PENDING_FOR_SPECIALIST_SUGGESTION
                    || order.getStatus() == OrderStatus.PENDING_FOR_SPECIALIST_SELECTION)) {

                fillSendSuggestion(suggestion, order, specialist);
                return true;
            }
        } else {
            throw new CustomizedNotFoundException("desired specialist not found");

        }
        return false;
    }

    @Override
    public Optional<Specialist> fetchSpecialistWithUsername(String username) {

        return specialistRepository.findSpecialistByUsername(username);
    }

    @Override
    @Transactional
    public Specialist createSpecialist(Specialist specialist) {

        return specialistRepository.save(specialist);
    }


    private byte[] checkFile(File file) throws IOException {
        byte[] bFile = null;
        FileFormat format = new FileFormat("jpg");
        //  file.getName().endsWith(".jpg");
        if (format.accept(file, file.getName()) && file.length() / 1024 <= 300) {
            bFile = new byte[(int) file.length()];

            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } else {
            throw new CustomizedFormatOrSizeFileException("format or size of file not allowed");
        }
        return bFile;
    }

    @Transactional
    Specialist fillSpecialist(Specialist specialist) {

        Credit credit = new Credit();
        specialist.setUserType(UserType.SPECIALIST);
        specialist.setStatus(SpecialistStatus.PENDING_CONFIRMATION);
        creditService.createCredit(credit);
        specialist.setCredit(credit);
        return specialist;
    }

    @Transactional
    void fillSendSuggestion(Suggestion suggestion, Order order, Specialist specialist) {

        order.addSuggestion(suggestion);
        order.setStatus(OrderStatus.PENDING_FOR_SPECIALIST_SELECTION);
        suggestion.setSpecialist(specialist);
        orderService.createOrder(order);// update order
        specialistRepository.save(specialist); //update specialist
        suggestionService.createSuggestion(suggestion);

    }

}
