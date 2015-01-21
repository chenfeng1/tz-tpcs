package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Role Dao 接口类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface RoleDao extends CrudRepository<Role,String> {

    //根据 By 后面的属性名查询
    Role findByCode(String code);

}
