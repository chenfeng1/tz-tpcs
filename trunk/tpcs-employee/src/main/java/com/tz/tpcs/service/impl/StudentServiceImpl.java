package com.tz.tpcs.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tz.tpcs.dao.StudentDao;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.service.StudentService;
import com.tz.tpcs.web.form.Pager;
/**
 *StudentService 实现类 
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	private static final int PAGE_SIZE = 2;
	
	@Resource
	private StudentDao studentDao;
	
	@Override
	public Pager<Student> findByPager(final String clazzname,final String realname,
			 final String degree,final String loanStatus, Pager<Student> pager) {
		if(pager == null){
			pager = new Pager<>();
		}
		pager.setPageSize(PAGE_SIZE);
		Page<Student> page = studentDao.findAll(new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Path<String> clazzNamePath = root.get("clazz").get("name");
				Path<String> realNamePath =root.get("realname");
				Path<Degree> degreePath =root.get("degree");
				Path<LoanStatus> loanStatusPath =root.get("loanStatus");
				Predicate p = cb.conjunction();
				if(StringUtils.isNotBlank(clazzname)){
					p=cb.and(p,cb.like(cb.lower(clazzNamePath), "%" + clazzname.toLowerCase()+"%"));
				}
				if(StringUtils.isNotBlank(realname)){
					p=cb.and(p,cb.like(cb.lower(realNamePath), "%" + realname.toLowerCase()+"%"));
				}
				p=cb.and(p , cb.equal(degreePath.as(String.class),degree));
				p=cb.and(p , cb.equal(loanStatusPath.as(String.class),loanStatus));
				return p;
			}
		}, new PageRequest(pager.getPageNumber()-1, pager.getPageSize(), new Sort(Sort.Direction.ASC, "createDate")));
		pager.setList(page.getContent());
		Long lTotal = page.getTotalElements();
        pager.setTotalCount(lTotal.intValue());
		return pager;
	}

	@Override
	public void save(Student student) {
		studentDao.save(student);
	}

	@Override
	public void update(Student student) {
		/*Student temp = studentDao.findOne(student.getId());
		try {
			temp=(Student) student.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}*/
		// TODO Auto-generated method stub
		studentDao.save(student);
	}

	@Override
	public Student getById(String id) {
		return studentDao.findOne(id);
	}

}
