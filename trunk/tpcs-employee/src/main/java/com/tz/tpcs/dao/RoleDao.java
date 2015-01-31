package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Role Dao 接口类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface RoleDao extends CrudRepository<Role,String> {

    /**
     * 根据 By 后面的属性名查询
     * @param code 代号
     * @return 角色对象实例
     */
    Role findByCode(String code);

    /**
     * 根据资源类型，查询匹配的角色名
     * @param value 资源值
     * @return 资源List
     */
    @Query("select r.code from Role r join r.resources res where res.value =:value")
    List<String> findCodesByResValue(@Param("value") String value);

}
