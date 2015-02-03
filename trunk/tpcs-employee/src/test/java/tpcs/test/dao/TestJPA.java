package tpcs.test.dao;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * ClazzDao 单元测试类
 */
public class TestJPA extends AbstractDaoTxTest {

    @Resource
    private EntityManager em;
    @Resource
    private ClazzDao clazzDao;

    /**
     * 演示使用同一组条件，
     * 先做分页查询，再做投影查询
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test01(){
        //初始化
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Clazz.class);
        //同一组条件(name like %1%)
        Root<Clazz> root = cq.from(Clazz.class);
        Path<String> name = root.get("name");
        cq.where(cb.like(name, "%1%"));
        //后面有其他条件再可以添加...

        //1.先做分页查询
        List<Clazz> list = em.createQuery(cq)
                            .setFirstResult(0)
                            .setMaxResults(2)
                            .getResultList();
        System.out.println(list);
        //2.再做投影查询
        cq.select(cb.countDistinct(root));
        Long total = (Long) em.createQuery(cq).getSingleResult();
        System.out.println("total:"+total);
    }

    /**
     * 分页多条件查询
     */
    @Test
    public void test02(){
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

}
