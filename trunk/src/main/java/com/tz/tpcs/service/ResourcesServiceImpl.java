package com.tz.tpcs.service;

import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.util.ResourcesUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private static final Logger logger = Logger.getLogger(ResourcesServiceImpl.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ResourcesDao resourcesDao;

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
}
