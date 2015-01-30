package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.web.form.Pager;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:58
 */
public class ProjectCaseDaoImpl implements ProjectCaseDaoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pager<ProjectCase> findByPager(String name, String code, Pager<ProjectCase> pager) {
        //初始化
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(ProjectCase.class);
        Root<Clazz> root = cq.from(ProjectCase.class);
        //设置查询条件
        if(StringUtils.isNotBlank(name)){
            Path<String> namePath = root.get("name");
            cq.where(cb.like(cb.lower(namePath), "%"+name.trim().toLowerCase()+"%"));
        }
        if(StringUtils.isNotBlank(code)){
            Path<String> codePath = root.get("code");
            cq.where(cb.like(cb.lower(codePath), "%"+code.trim().toLowerCase()+"%"));
        }
        //设置按seq排序
        cq.orderBy(cb.desc(root.get("modifyDate")));
        //1.先做分页查询
        List<ProjectCase> list = em.createQuery(cq)
                .setFirstResult((pager.getPageNumber()-1)*pager.getPageSize())
                .setMaxResults(pager.getPageSize())
                .getResultList();
        pager.setList(list);
        //2.再做投影查询
        cq.select(cb.countDistinct(root));
        Long total = (Long) em.createQuery(cq).getSingleResult();
        int iTotal = Integer.valueOf(String.valueOf(total));
        pager.setTotalCount(iTotal);
        return pager;
    }
}
