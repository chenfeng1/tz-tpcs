package com.tz.tpcs.entity;

/**
 * 性别枚举类
 * Created by Hu Jing Ling on 2015/1/23.
 */
public enum Gender {

    MALE, //男
    FEMALE; //女

    /*****
     * 把中文字符串转换成枚举常量 [add by yejf]
     * @param value
     * @return 一个枚举常量
     */
    public static Gender convert(String value){
        Gender instance = null;
        switch (value){
            case "男":
                instance = MALE;
                break;
            case "女":
                instance = FEMALE;
                break;
            //如果上面的值不匹配，则以 MALE为值
            default:
                instance = MALE;
                break;
        }
        return instance;
    }
}
