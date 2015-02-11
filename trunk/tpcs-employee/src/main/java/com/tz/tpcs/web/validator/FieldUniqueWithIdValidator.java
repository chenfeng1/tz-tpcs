//package com.tz.tpcs.web.validator;
//
//import com.tz.tpcs.service.FieldUniqueValidatorService;
//import com.tz.tpcs.web.form.FieldUniqueFormSupport;
//import org.apache.log4j.Logger;
//
//import javax.annotation.Resource;
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
///**
// * 自定义数据库唯一性校验类(JSR303规范组件)
// * 用于 {@linkplain com.tz.tpcs.web.validator.FieldUniqueWithId FieldUniqueWithId}
// * @author Hu Jing Ling
// * @version 1.0
// * @since 2015/2/9 17:26
// */
//public class FieldUniqueWithIdValidator implements ConstraintValidator<FieldUniqueWithId, FieldUniqueFormSupport> {
//
//    private static final Logger LOGGER = Logger.getLogger(FieldUniqueWithIdValidator.class);
//
//    @Resource
//    private ValidatorServiceCenter serviceCenter;
//
//    /**
//     * 为校验提供业务逻辑的接口
//     */
//    private FieldUniqueValidatorService service;
//
//    /**
//     * 需要校验的属性名
//     */
//    private String field;
//
//    /**
//     * 空参构造
//     */
//    public FieldUniqueWithIdValidator() {
//        LOGGER.debug("empty constructor...");
//    }
//
//    @Override
//    public void initialize(FieldUniqueWithId constraintAnnotation) {
//        LOGGER.debug("initialize() run...");
//        field = constraintAnnotation.field();
//        service = serviceCenter.getService(constraintAnnotation.service());
//        LOGGER.trace(service +" has used for validate field:" + field);
//    }
//
//    @Override
//    public boolean isValid(FieldUniqueFormSupport form, ConstraintValidatorContext context) {
//        LOGGER.debug("isValid() run...");
////        boolean result = service.validateField(field, value);
////        LOGGER.trace("validating result:"+result);
////        return result;
//        return false;
//    }
//
//}
