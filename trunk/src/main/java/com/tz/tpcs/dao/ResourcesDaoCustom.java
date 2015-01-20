package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
public interface ResourcesDaoCustom {

    /**
     * 根据 code 查询 Resources（包含下属子资源）
     * @param code
     * @return
     */
    Resources findByCodeWithChildren(String code);

}
