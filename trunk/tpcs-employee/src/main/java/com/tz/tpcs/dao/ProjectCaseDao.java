package com.tz.tpcs.dao;

import com.tz.tpcs.entity.ProjectCase;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * ProjectCase Dao 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 15:19
 */
public interface ProjectCaseDao extends CrudRepository<ProjectCase, String>, JpaSpecificationExecutor<ProjectCase> {

    /**
     * 根据name精确查询
     * @param name 项目名称
     * @return ProjectCase
     */
    ProjectCase findByName(String name);

    /**
     * 根据code精确查询
     * @param code 项目代号
     * @return ProjectCase
     */
    ProjectCase findByCode(String code);
}
