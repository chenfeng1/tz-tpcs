package com.tz.tpcs.dao;

import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.web.form.Pager;

/**
 * ProjectCase Dao 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 16:11
 */
public interface ProjectCaseDaoCustom {

    /**
     * 查询+分页功能
     * @param name 项目名
     * @param code 代号
     * @param pager 分页信息
     * @return 分页信息(含数据)
     */
    Pager<ProjectCase> findByPager(String name, String code, Pager<ProjectCase> pager);
}
