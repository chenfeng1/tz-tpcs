package com.tz.tpcs.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 17:24
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistValidator.class)
public @interface NotExist {

    /**
     * i18n key
     * @return
     */
    String message();

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

}
