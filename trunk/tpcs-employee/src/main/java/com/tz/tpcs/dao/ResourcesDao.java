package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * ResourceDao 接口类
 * Created by Hu jing ling on 2015/1/19.
 */
public interface ResourcesDao extends CrudRepository<Resources, String> {

    /**
     * 根据code查询资源，eager 取出下属子资源 (级别可通过 JPQL 调整)
     * @param code 代号
     * @return 一个资源对象
     */
    @Query(value = "select res from Resources as res " +
            "left join fetch res.children as c1 " +
//            "left join fetch c1.children as c2 " +
//            "left join fetch c2.children " +
            "where res.code =:code")
    Resources findResEager(@Param("code") String code);

    /**
     * 根据资源类型查询
     * @param type 类型
     * @return 资源Set集合
     */
    Set<Resources> findResByType(Resources.Type type);
}
