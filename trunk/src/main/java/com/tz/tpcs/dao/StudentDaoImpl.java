package com.tz.tpcs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.web.form.Paging;

/**
 * StudentDao 实现类
 * 
 * @author 管成功
 * @since 2015-01-26
 * @version 1.0
 */
@Transactional
public class StudentDaoImpl implements StudentDaoCustom {

	@Resource
	private StudentDao studentDao;

	@PersistenceContext
	private EntityManager em;

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("all")
	@Override
	public Paging getStudentByCondition(String clazz_name, String realname,
			String degree, String loanStatus, Integer pageNow, Integer pageSize) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Student.class);
		// 同一组条件(name like %1%)
		Root<Student> root = cq.from(Student.class);
		Path<String> realName = root.get("realname");
		Path<String> id = root.get("id");
		Path<Degree> degree2 = root.get("degree");
		Path<LoanStatus> loanStatus2 = root.get("loanStatus");

		Path<Date> createDate = root.get("createDate");// 按照创建时间来降序排列
		// 多表关联查询
		Join<Student, Clazz> depJoin = root.join(root.getModel()
				.getSingularAttribute("clazz", Clazz.class), JoinType.LEFT);
		Predicate p = cb.like(id, "%-%");// 类似于 where 1=1的作用
		Predicate p1 =null;
		if(clazz_name!=null){
		  p1 = cb.like(depJoin.get("name").as(String.class), "%"+clazz_name.toUpperCase()+"%");// 条件一：班级名
		}
		Predicate p2=null;
		if(realname!=null){
			
			p2 = cb.like(cb.lower(realName) , "%" + realname.toLowerCase() + "%");// 条件二：学员
		}
		Predicate p3 = cb.equal(degree2.as(String.class), degree);// 条件三：学历
		Predicate p4 = cb.equal(loanStatus2.as(String.class), loanStatus);// 条件四：贷款状态

		root.join("clazz", JoinType.LEFT);
		p = cb.and(p);
		// 动态组合条件
		if (clazz_name != null && clazz_name.trim().length()>0) {
			p = cb.and(p1);
		}
		if (realname != null && realname.trim().length()>0) {
			p = cb.and(p2, p);
		}
		if (null != degree && degree.trim().length()>0 && !degree.equals("-1")) {
			p = cb.and(p3, p);
		}
		if (null != loanStatus && loanStatus.trim().length()>0) {
			p = cb.and(p4, p);
		}
		if (null == pageNow) {
			pageNow = 1;
		}
		if (null == pageSize) {
			pageSize = 5;
		}
		// 后面有其他条件再可以添加...
		cq.where(p);
		cq.orderBy(cb.desc(createDate));
		// 1.先做分页查询
		List<Student> list = em.createQuery(cq)
				.setFirstResult(pageSize * (pageNow - 1))
				.setMaxResults(pageSize).getResultList();
		// 2.再做投影查询
		cq.select(cb.countDistinct(root));
		Long total = (Long) em.createQuery(cq).getSingleResult();

		Paging paging = new Paging();
		paging.setStudents(list);
		paging.setPageNow(pageNow);
		paging.setPageSize(pageSize);
		int pageCount = 0;
		if(total%pageSize==0){
			pageCount = (int) (total/pageSize);//总条数整除每页显示显示多少得到总页数 
		}else{
			pageCount = (int) (total/pageSize+1);
		}
		paging.setPageCount(pageCount);

		return paging;
	}

	@Override
	public void update(Student student) {
		em.merge(student);
	}
}
