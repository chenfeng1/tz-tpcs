package com.tz.tpcs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.tz.tpcs.entity.Area;

/**
 * 
 * @author guan
 *
 */
@SuppressWarnings("unchecked")
@Transactional
public class AreaDaoImpl implements AreaDaoCustom{

	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}
	

	@Override
	public List<Area> getAll(int level) {
		return em.createQuery("from Area where level =:level order by code")
				.setParameter("level", level).getResultList();
	}

	@Override
	public String getAreaId(String code) {
		return (String) em.createQuery("select id from Area where code=:code")
				.setParameter("code", code).getSingleResult();
	}

	@Override
	public List<Area> getAreaByParentId(String id) {
		return em.createQuery("from Area as a where a.parent.id=:id")
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public String getName(String code) {
		// TODO Auto-generated method stub
		return (String) em.createQuery("select name from Area as a where a.code=:code " +
				"order by code")
				.setParameter("code", code)
				.getSingleResult();
	}
}
