package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.web.form.Pager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *  Clazz Service 实现类
 * @author 管成功
 * @amender 胡荆陵
 */
@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

	private static final int PAGE_SIZE = 2;
	public static final Integer DEFAULT_PAGE_SIZE = 10;

	@Resource
	private ClazzDao clazzDao;
	
	@Override
	public void save(Clazz clazz) {
		clazzDao.save(clazz);
	}

	@Override
	public void deleteById(String id) {
		clazzDao.delete(id);
	}

	@Override
	public Clazz getById(String id) {
		return clazzDao.findOne(id);
	}

	@Override
	public Clazz findByName(String name) {
		
		return clazzDao.findByName(name);
	}

	@Override
	public void update(Clazz clazz) {
		Clazz temp = clazzDao.findOne(clazz.getId());
		temp.setName(clazz.getName());
		temp.setRoom(clazz.getRoom());
		temp.setOpen(clazz.getOpen());
		temp.setCount(clazz.getCount());
		temp.setAdvisor(clazz.getAdvisor());
		temp.setLecturer(clazz.getLecturer());
		temp.setTrainingDate(clazz.getTrainingDate());
		clazzDao.save(temp);
	}

	@Override
	public Pager<Clazz> findByPager(final String name, final Integer min, final Integer max,
			Pager<Clazz> pager) {
		if(pager == null){
			pager = new Pager<>();
		}
		/*if(pager.getPageSize()==PAGE_SIZE||pager.getPageSize()==DEFAULT_PAGE_SIZE){
			pager.setPageSize(PAGE_SIZE);
		}else{
			pager.setPageSize(pager.getPageSize());
		}*/	
		
		Page<Clazz> page =clazzDao.findAll(new Specification<Clazz>() {
			@Override
			public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> namePath = root.get("name");
				Path<Integer> countPath = root.get("count");
				Predicate p = cb.conjunction();
				if(StringUtils.isNotBlank(name)){
					p = cb.and(p, cb.like(cb.lower(namePath), "%" + name.toLowerCase() + "%"));
				}
				if(min!=null){
					p = cb.and(p, cb.ge(countPath, min));
				}
				if(max!=null){
					p = cb.and(p, cb.le(countPath, max));
				}
				return p;
			}
		}, new PageRequest(pager.getPageNumber()-1, pager.getPageSize(), new Sort(Sort.Direction.ASC, "modifyDate")));
		pager.setList(page.getContent());
		Long lTotal = page.getTotalElements();
        pager.setTotalCount(lTotal.intValue());
		return pager;
	}

}
