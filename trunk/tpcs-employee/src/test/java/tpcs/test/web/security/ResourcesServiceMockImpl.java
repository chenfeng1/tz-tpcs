package tpcs.test.web.security;

import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.util.IConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ResourcesService 实现类
 * @author Hu Jing Ling
 * @since 2015/1/20
 * @version 1.0
 */
@Service
@Profile(IConstant.PROFILE_MOCK)
public class ResourcesServiceMockImpl implements ResourcesService {

    @Override
    public List<Resources> findByCodes(String[] codes) {
        return null;
    }

    @Override
    public Set<Resources> findResByEmployeeId(String employeeId) {
        Set<Resources> resourcesSet = new HashSet<>();
        resourcesSet.add(new Resources());
        return resourcesSet;
    }

    @Override
    public Map<String, Set<String>> getRes2RoleMap() {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("mockRole");
        Set<String> set2 = new HashSet<>();
        set2.add("mockRole2");
        map.put("/clazz/list", set);
        map.put("/employees/list", set2);
        return map;
    }

}
