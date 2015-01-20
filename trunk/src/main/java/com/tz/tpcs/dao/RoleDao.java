package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Role Dao 接口类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface RoleDao extends CrudRepository<Role,String> {

    //根据getBy后面的属性名 code 属性查询
    Role getByCode(String code);

}
