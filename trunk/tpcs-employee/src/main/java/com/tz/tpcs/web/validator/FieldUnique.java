package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性数据库唯一性校验规则,
 * 加在表单封装类的属性名上方,
 * 适用于新增操作
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 17:24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldUniqueValidator.class)
//@Documented
public @interface FieldUnique {

    /**
     * i18n key prefix
     * @return
     */
    String messagePrefix();

    /**
     * message
     * @return
     */
    String message() default "";

    /**
     * 需校验的属性名数组
     * @return
     */
    String[] fields();

    /**
     * groups
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * payload
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 调用的service
     * @return
     */
    Class<? extends FieldUniqueValidatorService> service();
}
