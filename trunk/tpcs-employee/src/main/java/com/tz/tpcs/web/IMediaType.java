package com.tz.tpcs.web;

/**
 * 自定义 MediaType 扩展类
 * 如果 {@linkplain org.springframework.http.MediaType MediaType} 未提供，
 * 则可以在此类中进行扩展
 * @authod Hu Jing Ling
 * @since 2014/10/24
 */
public interface IMediaType {

    String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

}
