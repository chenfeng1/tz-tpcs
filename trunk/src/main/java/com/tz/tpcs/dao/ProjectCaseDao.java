package com.tz.tpcs.dao;

import org.springframework.data.repository.CrudRepository;

import com.tz.tpcs.entity.ProjectCase;



/**
 * 项目案例Dao 接口类
 * @author 管成功
 * @since 2015-01-23
 * @version 1.0
 *
 */
public interface ProjectCaseDao extends CrudRepository<ProjectCase,String>,ProjectCaseDaoCustom{

}
