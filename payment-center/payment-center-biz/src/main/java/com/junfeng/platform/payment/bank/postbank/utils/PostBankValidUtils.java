package com.junfeng.platform.payment.bank.postbank.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.postbank.request.BasePostBankRequest;

/**
 * 邮政银行验证空值
 *
 * @projectName:payment-center
 * @author:xiongh
 * @date:2019年3月4日 上午11:23:41
 * @version 1.0
 */
public class PostBankValidUtils {

    private PostBankValidUtils() {

    }

    public static <T extends BasePostBankRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }

}
