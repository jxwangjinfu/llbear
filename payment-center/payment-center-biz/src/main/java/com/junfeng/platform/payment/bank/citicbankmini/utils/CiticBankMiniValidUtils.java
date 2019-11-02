package com.junfeng.platform.payment.bank.citicbankmini.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.citicbankmini.request.CiticBankMiniBaseRequest;

public class CiticBankMiniValidUtils {

    private CiticBankMiniValidUtils() {
    }

    public static <T extends CiticBankMiniBaseRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }

}
