package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 部门 Dao 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/4 18:04
 */
public interface DepartmentDao extends CrudRepository<Department, String> {

    /**
     * 根据 level 查询
     * @param level
     * @return
     */
    @Query("select d from Department as d where d.level =:level")
    List<Department> findByLevel(@Param("level") int level);

    /**
     * 根据 name 查询
     * @param name 部门名
     * @return
     */
    Department findByName(String name);

    /**
     * 根据 id 查询 name
     * @param id 部门ID
     * @return 部门name
     */
    @Query("select d.name from Department as d where d.id =:id")
    String findNameById(@Param("id") String id);

}
