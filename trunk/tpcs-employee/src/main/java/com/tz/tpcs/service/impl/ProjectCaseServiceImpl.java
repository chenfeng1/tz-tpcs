package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.ProjectCaseDao;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.service.ProjectCaseService;
import com.tz.tpcs.web.form.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * ProjectCase Service 实现类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:55
 */
@Service
@Transactional
public class ProjectCaseServiceImpl implements ProjectCaseService {

    private static final int PAGE_SIZE = 2;

    @Resource
    private ProjectCaseDao projectCaseDao;

    @Override
    public Pager<ProjectCase> findByPager(String name, String code, Pager<ProjectCase> pager) {
        if(pager == null){
            pager = new Pager<>();
        }
        pager.setPageSize(PAGE_SIZE);
        return projectCaseDao.findByPager(name, code, pager);
    }

    @Override
    public void delete(String id) {
        ProjectCase projectCase = projectCaseDao.findOne(id);
        if(projectCase != null){
            //todo..添加后续的业务逻辑，比如此项目案例下属有项目记录...
            projectCaseDao.delete(id);
        }
    }

    @Override
    public void save(ProjectCase projectCase) {
        projectCaseDao.save(projectCase);
    }

    @Override
    public ProjectCase findByName(String name) {
        ProjectCase projectCase = projectCaseDao.findByName(name);
        return projectCase;
    }

    @Override
    public ProjectCase findByCode(String code) {
        ProjectCase projectCase = projectCaseDao.findByCode(code);
        return projectCase;
    }

    @Override
    public ProjectCase findById(String id) {
        return projectCaseDao.findOne(id);
    }

    @Override
    public void update(ProjectCase projectCase) {
        ProjectCase temp = projectCaseDao.findOne(projectCase.getId());
        temp.setName(projectCase.getName());
        temp.setCode(projectCase.getCode());
        temp.setDesc(projectCase.getDesc());
        temp.setVersion(projectCase.getVersion());
        if(StringUtils.isNotBlank(projectCase.getFunctionSpec())){
            temp.setFunctionSpec(projectCase.getFunctionSpec());
        }
        if(StringUtils.isNotBlank(projectCase.getSnapshot())){
            temp.setSnapshot(projectCase.getSnapshot());
        }
        projectCaseDao.save(temp);
    }

}
