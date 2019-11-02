package com.junfeng.platform.annotation;

import com.junfeng.platform.config.Swagger2AutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 描述
 *
 * @author 2fx0one
 * @version 1.0
 * @createDate 2019-10-18 09:52
 * @projectName pig
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Swagger2AutoConfiguration.class})
public @interface EnableJunFengSwagger2 {
}
