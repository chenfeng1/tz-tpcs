package com.tz.tpcs.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
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
	
	@Resource
    private Mapper mapper;
	
	@Override
	public Pager<Student> findByPager(final String clazzname,final String realname,
			 final String degree,final String loanStatus, Pager<Student> pager) {
		if(pager == null){
			pager = new Pager<>();
		}
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
				if(degree!=null){
					p=cb.and(p , cb.equal(degreePath.as(String.class),degree));
				}
				if(loanStatus!=null){
					p=cb.and(p , cb.equal(loanStatusPath.as(String.class),loanStatus));
				}
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
		Student temp =studentDao.findOne(student.getId());
		if(temp!=null){
			temp.setRealname(student.getRealname());
			temp.setAddress(student.getAddress());
			temp.setAddress2(student.getAddress2());
			temp.setBirthDate(student.getBirthDate());
			temp.setBakPhone(student.getBakPhone());
			temp.setCity(student.getCity());
			temp.setClazz(student.getClazz());
			temp.setCurrentLoc(student.getCurrentLoc());
			temp.setDegree(student.getDegree());
			temp.setDesignDate(student.getDesignDate());
			temp.setEmail(student.getEmail());
			temp.setEmergencyContact(student.getEmergencyContact());
			temp.setEmergencyPhone(student.getEmergencyPhone());
			temp.setGender(student.getGender());
			temp.setGraduationDate(student.getGraduationDate());
			temp.setIdentityCard(student.getIdentityCard());
			temp.setLevel(student.getLevel());
			temp.setLoanStatus(student.getLoanStatus());
			temp.setMajor(student.getMajor());
			temp.setNeedDesign(student.isNeedDesign());
			temp.setPaid(student.getPaid());
			temp.setPhone(student.getPhone());
			temp.setProvince(student.getProvince());
			temp.setQq(student.getQq());
			temp.setRemark(student.getRemark());
			temp.setSchool(student.getSchool());
			temp.setSource(student.getSource());
			temp.setStatus(student.getStatus());
			temp.setWorkingYears(student.getWorkingYears());
			temp.setWorkLoc(student.getWorkLoc());
			temp.setOwner(student.getOwner());
			temp.setVersion(student.getVersion());
		}
		studentDao.save(temp);
	}

	@Override
	public Student getById(String id) {
		return studentDao.findOne(id);
	}

	@Override
	public void deleteById(String id) {
		studentDao.delete(id);
	}

}
