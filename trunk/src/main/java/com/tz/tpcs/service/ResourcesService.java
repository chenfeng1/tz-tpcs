package com.tz.tpcs.service;

import com.tz.tpcs.entity.Resources;

import java.util.List;

/**
 * Resources Service 接口类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface ResourcesService {

    /**
     * 根据给定的 code 数组查询(含所有下属资源)
     * @param codes
     * @return
     */
    List<Resources> findByCodes(String[] codes);

}
