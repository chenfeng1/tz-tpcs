package com.tz.tpcs.service;

import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * RoleService 接口类
 * Created by Hu Jing Ling on 2015/1/21.
 */
public interface RoleService {
    /**
     * 根据一个角色集合，返回排序后的资源列表
     * @param roles
     * @return
     */
    List<Resources> getSortedResByRoles(Set<Role> roles);
}
