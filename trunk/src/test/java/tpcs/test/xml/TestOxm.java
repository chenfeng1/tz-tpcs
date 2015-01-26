package tpcs.test.xml;

import com.alibaba.fastjson.JSON;
import com.tz.tpcs.entity.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/23 19:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:xml/spring-oxm.xml")
public class TestOxm {

    @Resource
    private CastorMarshaller castorMarshaller;

    @Test
    public void test01ObjectToXml() throws Exception {
        Mobile mobileObject = new Mobile();
        mobileObject.setName("Nokia");
        mobileObject.setModel("N8");
        mobileObject.setPrice(32323D);

        FileOutputStream outputStream = new FileOutputStream(new File("D:/mobile.xml"));
        StreamResult xmlFileResult = new StreamResult(outputStream);

        castorMarshaller.marshal(mobileObject, xmlFileResult);
    }

    @Test
    public void test02XmlToObject() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("D:/mobile.xml"));
        StreamSource xmlFileSource = new StreamSource(inputStream);

        castorMarshaller.setTargetClass(Mobile.class);
        Mobile mobileObject = (Mobile) castorMarshaller.unmarshal(xmlFileSource);

        System.out.println(mobileObject);
    }

}
