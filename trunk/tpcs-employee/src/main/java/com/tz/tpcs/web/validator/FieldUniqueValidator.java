package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;
import org.apache.log4j.Logger;
import org.junit.Assert;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义数据库唯一性校验类(JSR303规范组件)
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 17:26
 */
public class FieldUniqueValidator implements ConstraintValidator<FieldUnique, String> {

    private static final Logger LOGGER = Logger.getLogger(FieldUniqueValidator.class);

    @Resource
    private ValidatorServiceCenter serviceCenter;

    /**
     * 为校验提供业务逻辑的接口
     */
    private FieldUniqueValidatorService service;

    /**
     * 需要校验的属性名
     */
    private String field;

    /**
     * 空参构造
     */
    public FieldUniqueValidator() {
        LOGGER.debug("empty constructor...");
    }

    @Override
    public void initialize(FieldUnique constraintAnnotation) {
        LOGGER.debug("initialize() run...");
        field = constraintAnnotation.field();
        service = serviceCenter.getService(constraintAnnotation.service());
        LOGGER.trace(service +" has used for validate field:" + field);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LOGGER.debug("is validating field:"+field+" with value:"+value);
        Assert.assertNotNull(field);
        Assert.assertNotNull(value);
        boolean result = service.validateField(field, value);
        LOGGER.trace("validating result:"+result);
        return result;
    }

}
