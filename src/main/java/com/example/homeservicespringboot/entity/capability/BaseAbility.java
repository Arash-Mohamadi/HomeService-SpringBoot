package com.example.homeservicespringboot.entity.capability;

import com.example.homeservicespringboot.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class BaseAbility extends BaseEntity<Long> {


}
