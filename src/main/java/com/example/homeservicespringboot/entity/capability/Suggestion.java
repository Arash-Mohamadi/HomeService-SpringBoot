package com.example.homeservicespringboot.entity.capability;


import com.example.homeservicespringboot.entity.users.Specialist;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Suggestion extends BaseAbility {
    @CreationTimestamp
    private LocalDate creationDate;
    @CreationTimestamp
    private LocalTime creationTime;
    private double price;

    private double duration; //  unit of hour

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)  //bidirectional
    private Specialist specialist;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE) //bidirectional
    private Order order;

    public Suggestion(double price, double duration) {
        this.price = price;
        this.duration = duration;
    }
}
