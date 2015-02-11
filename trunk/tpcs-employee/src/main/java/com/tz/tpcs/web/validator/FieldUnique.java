package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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
@Documented
public @interface FieldUnique {

    /**
     * 可以同时使用多个注解
     * {@linkplain com.tz.tpcs.web.validator.FieldUnique FieldUnique}
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * FieldUnique 数组
         * @return
         */
        FieldUnique[] value();
    }

    /**
     * message
     * @return
     */
    String message() default "{field.value.is.not.unique}";

    /**
     * 需校验的属性名
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
