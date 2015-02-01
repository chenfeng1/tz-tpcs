package com.tz.tpcs.service.impl;

import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by Hu Jing Ling on 2015/1/21.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private ResourcesService resourcesService;

    @Override
    public List<Resources> getSortedResByRoles(Set<Role> roles) {
        for(Role role : roles){
            role.getName();
        }
        return null;
    }
}
