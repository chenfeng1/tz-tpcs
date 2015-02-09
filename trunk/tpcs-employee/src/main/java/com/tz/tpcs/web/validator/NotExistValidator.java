package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.EmployeeService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 17:26
 */
public class NotExistValidator implements ConstraintValidator<NotExist, String> {

    private static final Logger LOGGER = Logger.getLogger(NotExistValidator.class);

    @Resource
    private EmployeeService employeeService;

    /**
     * 空参构造
     */
    public NotExistValidator() {
        LOGGER.debug("empty constructor...");
    }

    @Override
    public void initialize(NotExist constraintAnnotation) {
        //...
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context) {
        if(str.contains("a")){
            return false;
        }else {
            return true;
        }
    }

}
