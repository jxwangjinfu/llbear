package com.junfeng.platform.payment.bank.jxbank.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.junfeng.platform.payment.bank.jxbank.request.JxBankBaseRequest;

/**
 * 校验参数格式
 *
 * @projectName:tobacco-cloud-common
 * @author:xionghui
 * @date:2018年8月7日 上午10:31:09
 * @version 1.0
 */
public final class JxBankValidUtils {

    private JxBankValidUtils() {
    }

    public static <T extends JxBankBaseRequest> String valid(T t) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations != null && violations.size() > 0) {
            return violations.iterator().next().getMessage();
        }
        return null;
    }

}
