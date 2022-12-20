package com.example.homeservicespringboot.entity.capability;


import com.example.homeservicespringboot.entity.users.Specialist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.Set;


@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Services extends BaseAbility {
    @Column(unique = true)
    @NotBlank(message = "name should not contains null value or space . ")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "services",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<SubServices> subList;

    @ToString.Exclude
    @ManyToMany(mappedBy = "servicesSet")
    private Set<Specialist> specialistSet;

    public void addSpecialist(Specialist specialist){
        this.getSpecialistSet().add(specialist);
        specialist.getServicesSet().add(this);
    }

    public void addSubServices(SubServices subServices){
        this.getSubList().add(subServices);
        subServices.setServices(this);
    }

    public Services(String name) {
        this.name = name;
    }
}
