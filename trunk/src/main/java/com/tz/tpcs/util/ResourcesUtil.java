package com.tz.tpcs.util;

import com.tz.tpcs.entity.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Resources 实体类 工具类
 * Created by Hu Jing Ling on 2015/1/20.
 */
public class ResourcesUtil {


    /**
     * 将一个含有资源(含子资源)，转换为 List
     * @param res
     * @return
     */
    public static List<Resources> convertResToList(Resources res) {
        List<Resources> resourcesList = new ArrayList<>();
        resourcesList.add(res);
        recursiveAddResToList(res, resourcesList);
        return resourcesList;
    }

    /**
     * 算法: 递归添加子资源
     * @param res
     * @param resourcesList
     */
    private static void recursiveAddResToList(Resources res, List<Resources> resourcesList){
        Set<Resources> resourcesSet = res.getChildren();
        for(Resources subRes : resourcesSet){
            resourcesList.add(subRes);
            recursiveAddResToList(subRes, resourcesList);
        }
    }

}
