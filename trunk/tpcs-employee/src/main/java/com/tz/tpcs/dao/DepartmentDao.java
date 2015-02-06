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

}
