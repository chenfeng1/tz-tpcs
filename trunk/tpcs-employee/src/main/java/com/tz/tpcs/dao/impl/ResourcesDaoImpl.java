package com.tz.tpcs.dao.impl;

import com.tz.tpcs.dao.ResourcesDaoCustom;
import com.tz.tpcs.entity.Resources;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
public class ResourcesDaoImpl implements ResourcesDaoCustom {

    @Resource
    private EntityManager em;

    @SuppressWarnings("JpaQlInspection")
    @Override
    public Resources findByCodeWithChildren(String code) {
        String ql = "select res from Resources as res where res.code =:code";
        Resources res = (Resources) em.createQuery(ql).setParameter("code", code).getSingleResult();
        return res;
    }
}
