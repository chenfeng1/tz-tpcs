package tpcs.test.service;

import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/21
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRoleService extends AbstractServiceTxTest {

    @Resource
    private RoleDao roleDao;

    @Test
    public void test1(){
        //1.
        Set<Resources> resourcesSet = new LinkedHashSet<>();
        //用户的角色集合
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByCode("admin"));
        roles.add(roleDao.findByCode("classTeacher"));
        //2.
        for(Role role : roles){
            for(Resources res : role.getResources()){
                if(res.getType().equals(Resources.Type.FOLDER)){
                    resourcesSet.add(res);
                }
            }
        }
        //3.
        System.out.println(resourcesSet);
    }

}
