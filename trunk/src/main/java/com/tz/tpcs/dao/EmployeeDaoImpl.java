package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2015/1/16.
 */
public class EmployeeDaoImpl implements EmployeeDaoCustom {

    @PersistenceContext
    private EntityManager em;
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Employee findSingleByProp(String prop, Object value) {
        String ql = "select e from Employee as e where e."+prop+" = :value";
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
