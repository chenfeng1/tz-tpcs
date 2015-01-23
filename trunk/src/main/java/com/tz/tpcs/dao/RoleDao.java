package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Role Dao 接口类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface RoleDao extends CrudRepository<Role,String> {

    /**
     * 根据 By 后面的属性名查询
     */
    Role findByCode(String code);

    /**
     * 根据资源类型，查询匹配的角色名
     * @param value
     * @return
     */
    @Query("select r.code from Role r join r.resources res where res.value =:value")
    List<String> findCodesByResValue(@Param("value") String value);

}
