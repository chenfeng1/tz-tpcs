package com.tz.tpcs.service;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/10 9:24
 */
public interface FieldUniqueValidatorService {

    /**
     * 数据库 唯一性 校验方法
     * @param fieldName 属性名
     * @param fieldValue 属性值
     * @param id ID
     * @return 如果校验通过返回true,反之返回false
     */
    boolean validateField(String fieldName, String fieldValue, String id);

}
