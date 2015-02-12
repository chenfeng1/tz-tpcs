package com.tz.tpcs.entity;

/**
 * 
 * @author guan
 *
 */
public enum Degree {

	UNDERGRADUATE,//本科
	JUNIOR,//专科
	MASTER,//硕士
	DOCTOR,//博士
	HIGH,//高中
	OTHER;//其他

    /*****
     * 把中文字符串转换成枚举常量
     * @param value
     * @return 一个枚举常量
     */
    public static Degree convert(String value){
        Degree instance = null;
        switch (value){
            case "本科":
                instance = UNDERGRADUATE;
                break;
            case "专科":
                instance = JUNIOR;
                break;
            case "硕士":
                instance = MASTER;
                break;
            case "博士":
                instance = DOCTOR;
                break;
            case "高中":
                instance = HIGH;
                break;
            case "其他":
                instance = OTHER;
                break;
        }
        return instance;
    }
}
