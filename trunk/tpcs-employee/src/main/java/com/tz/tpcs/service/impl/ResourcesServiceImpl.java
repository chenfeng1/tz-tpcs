package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.util.ResourcesUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * ResourcesService 实现类
 * @author Hu Jing Ling
 * @since 2015/1/20
 * @version 1.0
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private static final Logger LOGGER = Logger.getLogger(ResourcesServiceImpl.class);

    @Resource
    private ResourcesDao resourcesDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private EmployeeDao employeeDao;

    /** 空参构造 */
    public ResourcesServiceImpl() {
        LOGGER.trace("ResourcesServiceImpl empty constructor...");
    }

    @Override
    public List<Resources> findByCodes(String[] codes) {
        List<Resources> list = new ArrayList<>();
        for(String code : codes){
            Resources res = resourcesDao.findResEager(code);
            List<Resources> tempList = ResourcesUtil.convertResToList(res);
            list.addAll(tempList);
        }
        return list;
    }

    @Override
    public Set<Resources> findResByEmployeeId(String employeeId) {
        //1.初始化 TreeSet
        //按照 Resources 的 seq 属性排序
        Set<Resources> resourcesSet = new TreeSet<>(new Comparator<Resources>(){
            @Override
            public int compare(Resources res1, Resources res2) {
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
                    resourcesSet.add(resourcesDao.findResEager(res.getCode()));
                }
            }
        }
        return resourcesSet;
    }

    @Override
    public Map<String, Set<String>> getRes2RoleMap() {
        //初始化返回值
        Map<String, Set<String>> map = new HashMap<>();
        //获得所有 URL 类型资源
        Set<Resources> resourcesSet = resourcesDao.findResByType(Resources.Type.URL);
        for(Resources res : resourcesSet){
            //查询匹配资源的角色
            List<String> list = roleDao.findCodesByResValue(res.getValue());
            //存入map
            map.put(res.getValue(), new HashSet<String>(list));
        }
        return map;
    }

}
