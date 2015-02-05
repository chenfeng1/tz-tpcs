package com.tz.tpcs.dao.impl;

import com.tz.tpcs.dao.EmployeeDaoCustom;
import com.tz.tpcs.entity.Employee;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2015/1/16.
 */
public class EmployeeDaoImpl implements EmployeeDaoCustom {

    private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class);

    @PersistenceContext
    private EntityManager em;
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * empty constructor
     */
    public EmployeeDaoImpl() {
        LOGGER.debug("EmployeeDaoImpl empty constructor...");
    }

    @Override
    public Employee findSingleByProp(String prop, Object value) {
        String ql = "select e from Employee as e left join fetch e.roles where e."+prop+" = :value";
        Query query = em.createQuery(ql);
        query.setParameter("value", value);
        @SuppressWarnings("unchecked")
        List<Employee> list = query.getResultList();
        if(list.size() ==0){
            return null;
        }else{
            return list.get(0);
        }
    }
}

