package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Employee;

/**
 * Employee 自定义扩展 Dao 接口类
 */
public interface EmployeeDaoCustom {

    /**
     * 根据属性名和属性值, 查询单个 Employee
     * @param prop 属性名
     * @param value 属性值
     * @return 符合条件的单个 Employee
     */
    Employee getSingleByProp(String prop, Object value);

}
