package com.tz.tpcs.service;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;

import java.util.List;
import java.util.Set;

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

    /**
     * 根据员工id，查询分配的资源集合
     * @param employeeId 员工id
     * @return
     */
    Set<Resources> findResByEmployeeId(String employeeId);
}
