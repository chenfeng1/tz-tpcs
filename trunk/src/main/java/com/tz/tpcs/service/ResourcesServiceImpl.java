package com.tz.tpcs.service;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.util.ResourcesUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ResourcesServiceImpl implements ResourcesService {

    private static final Logger logger = Logger.getLogger(ResourcesServiceImpl.class);

    @Resource
    private ResourcesDao resourcesDao;
    @Resource
    private EmployeeDao employeeDao;

    public ResourcesServiceImpl() {
        logger.trace("ResourcesServiceImpl empty constructor...");
    }

    @Override
    public List<Resources> findByCodes(String[] codes) {
        List<Resources> list = new ArrayList<>();
        for(String code : codes){
            Resources res = resourcesDao.findByCodeWithChildren(code);
            List<Resources> tempList = ResourcesUtil.convertResToList(res);
            list.addAll(tempList);
        }
        return list;
    }

    @Override
    public Set<Resources> findResByEmployeeId(String employeeId) {
        //1.初始化 TreeSet
        //按照 Resources 的 seq 属性排序
        Set<Resources> resourcesSet = new TreeSet<>(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Resources res1 = (Resources) o1;
                Resources res2 = (Resources) o2;
                if(res1.getSeq() == res2.getSeq()){
                    return 0;
                }else{
                    return res1.getSeq() > res2.getSeq() ? 1: -1;
                }
            }
        });

        //2.获得用户的角色集合
        Employee employee = employeeDao.findOne(employeeId);
        Set<Role> roleSet = employee.getRoles();
        //3.迭代角色集合
        for(Role role : roleSet){
            for(Resources res : role.getResources()){
                //判断是否属于 功能FOLDER
                if(res.getType().equals(Resources.Type.FOLDER)){
                    //查询子资源后，添加到set
                    resourcesSet.add(resourcesDao.getResEager(res.getCode()));
                }
            }
        }

        return resourcesSet;
    }

}
