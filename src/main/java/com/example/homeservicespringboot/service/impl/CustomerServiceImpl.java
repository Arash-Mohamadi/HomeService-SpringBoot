package com.example.homeservicespringboot.service.impl;

import com.example.homeservicespringboot.entity.capability.*;
import com.example.homeservicespringboot.entity.enums.OrderStatus;
import com.example.homeservicespringboot.entity.enums.UserType;
import com.example.homeservicespringboot.entity.users.Customer;
import com.example.homeservicespringboot.exception.CustomizedIllegalArgumentException;
import com.example.homeservicespringboot.exception.CustomizedInvalidStatusException;
import com.example.homeservicespringboot.exception.CustomizedNotFoundException;
import com.example.homeservicespringboot.repository.CustomerRepository;
import com.example.homeservicespringboot.service.*;
import com.example.homeservicespringboot.validation.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CreditService creditService;
    private OrderService orderService;
    private OpinionService opinionService;
    private SuggestionService suggestionService;
    private SpecialistService specialistService;
    private SubServicesService subServicesService;

    // constructor

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CreditService creditService,
                               OrderService orderService,
                               OpinionService opinionService,
                               SuggestionService suggestionService,
                               SpecialistService specialistService,
                               SubServicesService subServicesService) {

        this.customerRepository = customerRepository;
        this.creditService = creditService;
        this.orderService = orderService;
        this.opinionService = opinionService;
        this.suggestionService = suggestionService;
        this.specialistService = specialistService;
        this.subServicesService = subServicesService;
    }

    @Override
    @Transactional
    public boolean signup(Customer customer) {
        Credit credit = new Credit();
        try {
            //   Validation.checkEntity(customer);
            customer.setUserType(UserType.CUSTOMER);
            creditService.createCredit(credit);
            customer.setCredit(credit);
            customerRepository.save(customer);
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
    public Customer editPassword(String username, String password) {
        Customer customer;
        // validate password
        customer = customerRepository.findCustomerByUsername(username).orElseThrow(
                () -> new CustomizedNotFoundException("desired customer not found ."));
        customer.setPassword(password);
        try {
            Validation.checkEntity(customer);
            customerRepository.save(customer);
            return customer;
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
    public boolean registerOrder(Order order, String subServiceName, String customerUsername) {
        SubServices subServices = null;
        Customer customer = customerRepository.findCustomerByUsername(customerUsername).orElseThrow(
                () -> new CustomizedNotFoundException("desired subServicesService not found ."));
        subServices = subServicesService.fetchSubServiceWithName(subServiceName).orElseThrow(
                () -> new CustomizedNotFoundException("desired subServicesService not found ."));

        try {
            if (order.getPrice() >= subServices.getBasePrice()) {
                subServices.addOrder(order);
                order.setStatus(OrderStatus.PENDING_FOR_SPECIALIST_SUGGESTION);
                order.setCustomer(customer);
                orderService.createOrder(order);
                return true;
            } else {
                throw new CustomizedInvalidStatusException("price of order less than base price subservice");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    @Transactional
    public boolean registerOpinion(Opinion opinion, Long orderId) {
        Order order = null;
        List<BaseAbility> list;
        order = orderService.fetchOrderById(orderId).orElseThrow(
                () -> new CustomizedNotFoundException(" desired order not found ."));

        if (order.getStatus() != OrderStatus.PAID) {
            throw new CustomizedInvalidStatusException(" order should 'PAID' ");
        } else {
            list = setOrder(order, opinion); // blow page
        }
        try {

            opinionService.createOpinion((Opinion) list.get(1)); // save opinion
          //  orderService.createOrder((Order) list.get(0)); // update order
            return true;
        } catch (Exception e) {
        //    System.out.println(e.getMessage());
          e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean selectSpecialist(Long suggestionId, Long orderId) {
        Order order = orderService.fetchOrderById(orderId).get();
        Suggestion suggestion = suggestionService.findSuggestionWithId(suggestionId).get();
        suggestion.getOrder().setStatus(OrderStatus.PENDING_FOR_SPECIALIST_COMING);
        suggestion.getOrder().setSpecialist(suggestion.getSpecialist());
        try {
            suggestionService.createSuggestion(suggestion); // update
            orderService.createOrder(order);// update
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean changeStatusOrderToStarted(Long orderId) {
        Order order = orderService.fetchOrderById(orderId).get();

            try {
                if (LocalDate.now().isAfter(order.getCreationDate())){

                        order.setStatus(OrderStatus.STARTED);
                        orderService.createOrder(order); // update
                        return true;

            }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return false;
    }

    @Override
    public List<Suggestion> showAllSuggestionWithPrice(Long orderId) {

        return suggestionService.findSuggestionWithPrice(orderId);

    }

    @Override
    public List<Suggestion> showAllSuggestionWithScore(Long orderId) {

        return suggestionService.findSuggestionWithSpecialistScore(orderId);
    }



    public List<BaseAbility> setOrder(Order order, Opinion opinion) {
        List<BaseAbility> list = new ArrayList<BaseAbility>();
        order.setOrderWithOpinion(opinion);
        opinion.setSpecialist(order.getSpecialist());
        opinion.setCustomer(order.getCustomer());
        order.getSpecialist().setScoreAvg(opinion.getScore());
        list.add(0, order);
        list.add(1, opinion);
        return list;


    }
}
