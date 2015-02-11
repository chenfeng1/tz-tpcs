package com.tz.tpcs.service;

import java.util.List;

import com.tz.tpcs.entity.Role;

/**
 *role service 接口类
 */
public interface RoleService {
	/**
	 * 根据给定的code数组查询
	 * @param codes
	 * @return
	 */
	List<Role> findByCodes(String[] codes);
}
