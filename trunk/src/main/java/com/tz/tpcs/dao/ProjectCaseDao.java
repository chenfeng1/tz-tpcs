package com.tz.tpcs.dao;

import com.tz.tpcs.entity.ProjectCase;
import org.springframework.data.repository.CrudRepository;

/**
 * ProjectCase Dao 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 15:19
 */
public interface ProjectCaseDao extends CrudRepository<ProjectCase, String>, ProjectCaseDaoCustom {
}
