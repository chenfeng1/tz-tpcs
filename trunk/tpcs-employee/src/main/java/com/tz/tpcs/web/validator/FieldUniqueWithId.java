package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性数据库唯一性校验规则(指定id除外),
 * 加在表单封装类的类名上方,
 * 适用于更新操作
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/11 13:04
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldUniqueValidator.class)
public @interface FieldUniqueWithId {

    /**
     * i18n key
     * @return
     */
    String message();

    /**
     * 需要唯一性(根据id)校验的属性名
     * @return
     */
    String field();

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
