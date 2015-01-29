package com.tz.tpcs.dao;

import java.io.Serializable;

import com.tz.tpcs.entity.Resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
public class ResourcesDaoImpl implements ResourcesDaoCustom,Serializable {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("JpaQlInspection")
    @Override
    public Resources findByCodeWithChildren(String code) {
        String ql = "select res from Resources as res where res.code =:code";
        Resources res = (Resources) em.createQuery(ql).setParameter("code", code).getSingleResult();
        return res;
    }
}
