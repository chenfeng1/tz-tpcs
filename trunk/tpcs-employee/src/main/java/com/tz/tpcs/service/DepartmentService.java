package com.tz.tpcs.service;

import com.tz.tpcs.entity.Department;

import java.util.List;

/**
 * 部门 Service 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 11:28
 */
public interface DepartmentService {

    /**
     * 获得部门树
     * @return List<Department>
     */
    List<Department> getDeptTree();

    /**
     * 根据部门ID检查和删除
     * @param id 部门ID
     * @return 结果字符串
     */
    String checkAndDelete(String id);
}
