package com.tz.tpcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.RoleService;
import com.tz.tpcs.util.IConstant;

/**
 * role service 接口实现类 
 */
@Service
@Transactional
@Profile(IConstant.PROFILE_PRODUCTION)
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	@Override
	public List<Role> findByCodes(String[] codes) {
		List<Role> list = new ArrayList<Role>();
		for(String code:codes){
			Role role = roleDao.findByCode(code);
			list.add(role);
		}
		return list;
	}

}
