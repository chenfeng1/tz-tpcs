package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * ResourceDao 接口类
 * Created by Hu jing ling on 2015/1/19.
 */
public interface ResourcesDao extends CrudRepository<Resources, String>, ResourcesDaoCustom {

    /**
     * 根据code查询资源，eager 取出下属子资源 (级别可通过 JPQL 调整)
     * @param code
     * @return
     */
    @Query(value = "select res from Resources as res " +
            "left join fetch res.children as c1 " +
//            "left join fetch c1.children as c2 " +
//            "left join fetch c2.children " +
            "where res.code =:code")
    Resources getResEager(@Param("code") String code);

}
