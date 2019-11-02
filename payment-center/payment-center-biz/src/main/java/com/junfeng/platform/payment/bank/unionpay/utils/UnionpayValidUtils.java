package com.junfeng.platform.payment.bank.unionpay.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.unionpay.request.UnionpayBaseRequest;

public final class UnionpayValidUtils {

    public UnionpayValidUtils() {

    }

    public static <T extends UnionpayBaseRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }

}
