package com.example.homeservicespringboot.entity.capability;

import com.example.homeservicespringboot.entity.enums.OrderStatus;
import com.example.homeservicespringboot.entity.users.Customer;
import com.example.homeservicespringboot.entity.users.Specialist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseAbility {
    @CreationTimestamp
    private LocalDate creationDate;

    @CreationTimestamp
    private LocalTime creationTime;

    private double price;

    private  LocalTime startWork;

    private LocalDate date;

    private String description;
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE)
    private List<Suggestion> suggestions;

    @ToString.Exclude
    @ManyToOne //bidirectional
    private Customer customer;

    @ManyToOne //bidirectional
    @ToString.Exclude
    private SubServices subServices;


    @OneToOne //bidirectional
    @ToString.Exclude
    private Opinion opinion;

    @ManyToOne(cascade = CascadeType.MERGE) //bidirectional
    @ToString.Exclude
    private Specialist specialist;

    public void addSuggestion(Suggestion suggestion){
        this.getSuggestions().add(suggestion);
        suggestion.setOrder(this);
    }

    public void setOrderWithOpinion(Opinion opinion){
        this.setOpinion(opinion);
        opinion.setOrder(this);
    }

    public Order( double price,
                 LocalTime startWork, LocalDate date,
                 String description, String address) {

        this.price = price;
        this.startWork = startWork;
        this.date = date;
        this.description = description;
        this.address = address;
    }
}
