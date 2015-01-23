package com.tz.tpcs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.web.form.Paging;
@Transactional
public class ProjectCaseDaoImpl implements ProjectCaseDaoCustom{

	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}


}
