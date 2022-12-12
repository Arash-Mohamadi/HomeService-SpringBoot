package com.example.homeservicespringboot.validation;


import com.example.homeservicespringboot.entity.BaseEntity;
import com.example.homeservicespringboot.exception.CustomizedIllegalArgumentException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class Validation {

    private static ValidatorFactory validatorFactory;

    static {
        validatorFactory = jakarta.validation.Validation.buildDefaultValidatorFactory();
    }

    public static ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }

    public static void checkEntity(BaseEntity<Long> entity) {
        Set<ConstraintViolation<BaseEntity<Long>>> constraintViolations = getValidatorFactory()
                .getValidator()
                .validate(entity);


        if (!constraintViolations.isEmpty())
            throw new CustomizedIllegalArgumentException(constraintViolations.toString());
    }
}
