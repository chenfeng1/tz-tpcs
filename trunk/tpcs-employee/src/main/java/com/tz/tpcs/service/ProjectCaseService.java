package com.tz.tpcs.service;

import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.web.form.Pager;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:17
 */
public interface ProjectCaseService {


    /**
     * 查询+分页功能
     * @param name 项目名
     * @param code 代号
     * @param pager 分页信息
     * @return 分页信息(含数据)
     */
    Pager<ProjectCase> findByPager(String name, String code, Pager<ProjectCase> pager);

    /**
     * 删除ProjectCase
     * @param id ProjectCase ID
     */
    void delete(String id);

    /**
     * 保存 ProjectCase
     * @param projectCase 项目案例实例
     */
    void save(ProjectCase projectCase);

    /**
     * 检查名称
     * @param name 名称
     * @return 如果存在返回 true;如果不存在返回false
     */
    ProjectCase findByName(String name);

    /**
     * 检查代号
     * @param code 代号
     * @return 如果存在返回 true;如果不存在返回false
     */
    ProjectCase findByCode(String code);

    /**
     * 根据 ID 查询
     * @param id ID
     * @return ProjectCase
     */
    ProjectCase findById(String id);

    /**
     * 更新
     * @param projectCase
     */
    void update(ProjectCase projectCase);
}
