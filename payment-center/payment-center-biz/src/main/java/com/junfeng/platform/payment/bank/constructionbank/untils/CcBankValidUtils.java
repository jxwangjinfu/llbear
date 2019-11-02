package com.junfeng.platform.payment.bank.constructionbank.untils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.constructionbank.request.ConstructionBankBaseRequest;

public final class CcBankValidUtils {

    private CcBankValidUtils() {
    }

    public static <T extends ConstructionBankBaseRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }

}
