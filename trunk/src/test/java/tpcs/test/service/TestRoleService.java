package tpcs.test.service;

import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tpcs.test.dao.BaseTest;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/21
 */
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
public class TestRoleService extends BaseTest{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private RoleDao roleDao;

    @PersistenceContext
    private EntityManager em;

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


//        //拿到这些角色的id集合
//        String[] ids = new String[role.size()];
//        int i=0;
//        for(Role ro:role){
//            ids[i] = ro.getId();
//            i++;
//        }
        //拿到所有角色资源
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery(Role.class);
//        Root root = cq.from(Role.class);
//        cq.select(root);
//
//        //The equivalent JPQL query is:SELECT p FROM Pet p
////        SELECT p
//
//        TypedQuery<Resources> q = em.createQuery(cq);
//        List<Pet> allPets = q.getResultList();
//
//
//
//        Criteria criteria1 = getSession().createCriteria(Resources.class);
//        Criteria cuserSet1 = criteria1.createCriteria("role");
//        cuserSet1.add(Restrictions.in("id", ids));
////		cuserSet1.add(Restrictions.eq("id", ids[0]));
//        criteria1.setFetchMode("childs", FetchMode.JOIN);
//        criteria1.addOrder(Order.asc("seq"));
//        List<Resources> list = criteria1.list();
//        Iterator<Resources> iter = list.iterator();
//        //放入到set中,除去重复资源
//        Set<Resources> parentSet = new LinkedHashSet<>();
//        while(iter.hasNext()){
//            Resources r = iter.next();
//            parentSet.add(r);
//        }
    }

}
