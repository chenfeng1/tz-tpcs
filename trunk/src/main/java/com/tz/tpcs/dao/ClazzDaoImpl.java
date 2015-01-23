package com.tz.tpcs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;

/**
 * ClazzDao 实现类
 * @author 管成功
 * @since 2015-01-22
 * @version 1.0
 */
@Transactional
public class ClazzDaoImpl implements ClazzDaoCustom {

	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Clazz> getAllByCondition(String name, Integer min, Integer max) {
		StringBuilder hql = new StringBuilder(
				"select c from Clazz as c where 1=1");
		if (null != name && name.toString().length() > 0) {
			hql.append(" and c.name='" + name + "'");
		}
		if (null != min) {
			hql.append(" and c.count>=" + min);
		}
		if (null != max) {
			hql.append(" and c.count<=" + max);
		}

		Query query = em.createQuery(hql.toString());
		return query.getResultList();
	}

	@Override
	public void delById(String id) {
		String hql = "delete from Clazz as c where c.id='" + id + "'";
		em.createQuery(hql).executeUpdate();
	}

	@Override
	public void update(Clazz clazz) {
	//	clazz = em.find(Clazz.class, clazz.getId());
		String string = clazz.getClaz_name();
		clazz.setClaz_name(string);
		em.merge(clazz);
	}

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
		List<Clazz> list = em.createQuery(hql.toString()).setFirstResult(pageSize*(pageNow-1)).setMaxResults(pageSize).getResultList();
		paging.setClazzs(list);
		return paging;
	}
	
	/**
	 * 获取条件内的总条数
	 */
	@Override
	public Long getRowCount(String name,Integer min,Integer max){
		//构建CriteriaBuilder工厂对象
	/*	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Clazz> criteriaQuery = criteriaBuilder.createQuery(Clazz.class);
		Root<Clazz> clazz = criteriaQuery.from(Clazz.class);
		Predicate condition = criteriaBuilder.gt(clazz.getC, min);
		criteriaQuery.where(condition);
		TypedQuery<Clazz> typedQuery = em.createQuery(criteriaQuery);
		List<Clazz> result = typedQuery.getResultList();
		System.out.println(result.size());
		*/
		
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
		//return null;
	}
}
