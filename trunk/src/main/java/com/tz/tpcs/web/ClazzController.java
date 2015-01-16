package com.tz.tpcs.web;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Clazz 控制器类
 */
@RestController
public class ClazzController {

    @Resource
    private ClazzDao clazzDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "Hello World!";
    }

    /*@RequestMapping(value = "/customer/save")
    public String save(){
        Customer customer = new Customer();
        customer.setName("jack");
        customer.setAge(23);
//        customerDao.save(customer);
        return "/test.html";
    }*/

    @RequestMapping(value = "/classes/{id}")
    public Clazz get(@PathVariable String id){
        Clazz clazz = clazzDao.findOne(id);
        return clazz;
    }

    @RequestMapping(value = "/classes", method= RequestMethod.GET)
    public List<Clazz> list(){
        List<Clazz> customerList = (List<Clazz>) clazzDao.findAll();
        return customerList;
    }

}
