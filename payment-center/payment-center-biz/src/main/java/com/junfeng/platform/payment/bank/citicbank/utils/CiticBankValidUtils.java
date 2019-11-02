package com.junfeng.platform.payment.bank.citicbank.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.citicbank.request.CiticBankBaseRequest;

public final class CiticBankValidUtils {

    private CiticBankValidUtils() {
    }

    public static <T extends CiticBankBaseRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }
}
