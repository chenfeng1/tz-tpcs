package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;
import org.springframework.data.repository.CrudRepository;

/**
 * ResourceDao 接口类
 * Created by Hu jing ling on 2015/1/19.
 */
public interface ResourcesDao extends CrudRepository<Resources, String>, ResourcesDaoCustom {

}
