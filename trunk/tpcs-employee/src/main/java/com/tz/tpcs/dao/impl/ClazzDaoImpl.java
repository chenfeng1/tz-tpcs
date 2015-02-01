package com.tz.tpcs.dao.impl;

import com.tz.tpcs.dao.ClazzDaoCustom;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


/**
 * ClazzDao 实现类
 * @author 管成功
 * @amender 胡荆陵
 * @since 2015-02-01
 */
@Transactional
public class ClazzDaoImpl implements ClazzDaoCustom {

	@Resource
	private EntityManager em;

	/**
	 * 多条件分页查询
	 */
	@Override
	public Paging getAll(String name, Integer min, Integer max,
			Integer pageSize, Integer pageNow) {
		StringBuilder hql = new StringBuilder(
				"from Clazz as c where 1=1");
		if (null != name && name.toString().length() > 0) {
			hql.append(" and lower(c.name) like '%" + name.toLowerCase() + "%'");
		}
		if (null != min) {
			hql.append(" and c.count>=" + min);
		}
		if (null != max) {
			hql.append(" and c.count<=" + max);
		}
		hql.append(" order by c.createDate desc");
		
		Long rowCount = this.getRowCount(name, min, max);
		int pageCount = 0;//总页数
		if(rowCount%pageSize==0){
			pageCount = (int) (rowCount/pageSize);//总条数整除每页显示显示多少得到总页数 
		}else{
			pageCount = (int) (rowCount/pageSize+1);
		}
		Paging paging = new Paging();
		paging.setPageCount(pageCount);
		paging.setPageNow(pageNow);
		paging.setPageSize(pageSize);
		@SuppressWarnings("unchecked")
		List<Clazz> list = em.createQuery(hql.toString())
							.setFirstResult(pageSize * (pageNow - 1))
							.setMaxResults(pageSize)
							.getResultList();
		paging.setClazzs(list);
		return paging;
	}
	
	/**
	 * 获取条件内的总条数
	 */
	public Long getRowCount(String name,Integer min,Integer max){
		StringBuilder hql = new StringBuilder(
				"select count(*) from Clazz as c where 1=1");
		if (null != name && name.toString().length() > 0) {
			hql.append(" and c.name like '%" + name + "%'");
		}
		if (null != min) {
			hql.append(" and c.count>=" + min);
		}
		if (null != max) {
			hql.append(" and c.count<=" + max);
		}
		Query query = em.createQuery(hql.toString());
		Object count = query.getSingleResult();
		return Long.valueOf(String.valueOf(count));
	}

}
