package tpcs.test.service;

import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.service.ResourcesService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResourcesService extends AbstractServiceTxTest {

    @Resource
    private ResourcesService resourcesService;

    @Test
    public void test1FindResByEmployee(){
        //1.
        String[] array = new String[]{"menu_clazz", "menu_student"};
        //2.
        List<Resources> list = resourcesService.findByCodes(array);
        //3.
        System.out.println(list.size());
        for(Resources res : list){
            System.out.println(res);
        }
    }

    @Test
    public void test2FindResByEmployee(){
        //1.
        String employeeId = "833c0e7c-ed89-4655-ae1e-9f2940ebc2bb";
        //2.
        Set<Resources> resourcesSet = resourcesService.findResByEmployeeId(employeeId);
        //3.
        System.out.println(resourcesSet);
    }

    @Test
    public void test3FindResByEmployee(){
        Map<String,Set<String>> map = resourcesService.getRes2RoleMap();
        System.out.println(map);
    }

}
