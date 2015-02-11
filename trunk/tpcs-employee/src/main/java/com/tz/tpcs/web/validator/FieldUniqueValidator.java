package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;
import com.tz.tpcs.web.form.FieldUniqueFormSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * 自定义数据库唯一性校验类(JSR303规范组件)
 * 用于 {@linkplain com.tz.tpcs.web.validator.FieldUnique FieldUnique}
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 17:26
 */
public class FieldUniqueValidator implements ConstraintValidator<FieldUnique, FieldUniqueFormSupport> {

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
     * i18n key 前缀
     */
    private String messagePrefix;

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
    }

    @Override
    public boolean isValid(FieldUniqueFormSupport form, ConstraintValidatorContext context) {
        LOGGER.debug("is validating...");
        Assert.assertNotNull(field);
        Assert.assertNotNull(service);
        boolean isValid = false;
        try {
            String value = BeanUtils.getSimpleProperty(form, field);
            String id = BeanUtils.getSimpleProperty(form, "id");
            //通过查询数据库进行校验
            isValid = service.validateField(field, value, id);
            LOGGER.trace("isValid:"+isValid);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return isValid;

        /*Assert.assertNotNull(fields.length>0);
        Assert.assertNotNull(service);
        boolean isValid = true;
        for(String field : fields){
            try {
                String value = BeanUtils.getSimpleProperty(form, field);
                String id = BeanUtils.getSimpleProperty(form, "id");
                //通过查询数据库进行校验
                boolean b = service.validateField(field, value, id);
                if(!b){
                    isValid = false;
                    context.disableDefaultConstraintViolation();
                    //动态拼接一个i18n key
                    //比如: {employee.number.already.exist}
                    String messageKey = "{"+messagePrefix+"."+field+".already.exist}";
                    context.buildConstraintViolationWithTemplate(messageKey)
                            .addPropertyNode(field)
                            .addConstraintViolation();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return isValid;*/
    }

}
