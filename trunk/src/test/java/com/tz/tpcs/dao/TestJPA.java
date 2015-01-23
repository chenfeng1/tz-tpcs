package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

/**
 * ClazzDao 单元测试类
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestJPA extends BaseTest{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private EntityManager em;

    /**
     * 演示使用同一组条件，
     * 先做分页查询，再做投影查询
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testPageAndTotal(){
        //初始化
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Clazz.class);
        //同一组条件(name like %1%)
        Root<Clazz> root = cq.from(Clazz.class);
        Path<String> name = root.get("name");
        cq.where(cb.like(name, "%1%"));
        //后面有其他条件再可以添加...

        //1.先做分页查询
        List<Clazz> list = em.createQuery(cq)
                            .setFirstResult(0)
                            .setMaxResults(2)
                            .getResultList();
        System.out.println(list);
        //2.再做投影查询
        cq.select(cb.countDistinct(root));
        Long total = (Long) em.createQuery(cq).getSingleResult();
        System.out.println("total:"+total);
    }

}
