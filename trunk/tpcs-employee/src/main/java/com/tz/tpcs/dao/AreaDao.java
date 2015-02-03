package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Area;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Area Dao 接口类
 * @author guan
 * @amender 胡荆陵
 * @since 2015-1-27
 */
public interface AreaDao extends CrudRepository<Area, String> {

    /**
     * 根据id来查找
     */
    @Query("select a from Area as a where a.parent.divisionCode =:code")
    List<Area> findByParentCode(@Param("code") String code);

    /**
     * 根据level来查
     */
    @Query("select a from Area as a where a.level =:level order by a.divisionCode")
    List<Area> findByLevel(@Param("level") int level);

    /**
     * 根据 code 查找 name
     */
    @Query("select name from Area as a where a.divisionCode=:code")
    String findNameByCode(@Param("code") String code);
}
