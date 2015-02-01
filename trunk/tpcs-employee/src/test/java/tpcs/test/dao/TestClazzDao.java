package tpcs.test.dao;


import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClazzDao 单元测试类
 * @author 胡荆陵
 */
@TransactionConfiguration(defaultRollback = false)//是否回滚测试数据
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClazzDao extends BaseTest{

    @Resource
    private ClazzDao clazzDao;
    
    private Clazz clazz;
    
    @Before
    public void initCustomer(){
        clazz = new Clazz();
        clazz.setName("testClazz");
    }

    @Test
    public void test01findOne(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        Assert.assertEquals(c, clazz);
    }

    @Test
    public void test02update(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        c.setStatus(Clazz.ClazzStatus.PHASE1);
        clazzDao.save(c);
    }

    @Test
    public void test03delete(){
        clazzDao.save(clazz);
        clazzDao.delete(clazz);
    }

    @Test
    public void test04BatchSave(){
        DataFactory df = new DataFactory();
        List<Clazz> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Clazz clazz = new Clazz();
            clazz.setName("JSD140" + i);
            clazz.setOpen(df.getBirthDate());
            clazz.setCount(df.getNumberBetween(10, 50));
            list.add(clazz);
        }
        clazzDao.save(list);
    }

    /**
     * 分页多条件查询
     */
    @Test
    public void test05Pager(){
        //1.测试参数
        final String name = "js";
        final String min = "10";
        final String max = "40";
        //2.
        Page<Clazz> page = clazzDao.findAll(new Specification<Clazz>() {
            @Override
            public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> namePath = root.get("name");
                Path<Integer> countPath = root.get("count");
                Predicate p = cb.conjunction();
                if(StringUtils.isNotBlank(name)){
                    p = cb.and(p, cb.like(cb.lower(namePath), "%" + name.toLowerCase() + "%"));
                }
                if(NumberUtils.isDigits(min)){
                    Integer iMin = Integer.valueOf(min);
                    p = cb.and(p, cb.ge(countPath, iMin));
                }
                if(NumberUtils.isNumber(max)){
                    Integer iMax = Integer.valueOf(max);
                    p = cb.and(p, cb.le(countPath, iMax));
                }
                return p;
            }
        }, new PageRequest(0, 5, new Sort(Sort.Direction.ASC, "name")));
        //3.
        System.out.println(page);
    }

    /**
     * 根据班级名来查找班级
     */
    @Test
    public void test06FindByName(){
        clazzDao.save(clazz);
    	Clazz c = clazzDao.findByName("testClazz");
    	System.out.println(c);
    }
}
