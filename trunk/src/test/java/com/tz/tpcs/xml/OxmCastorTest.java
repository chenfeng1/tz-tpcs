package com.tz.tpcs.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.castor.CastorMarshaller;

public class OxmCastorTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        CastorMarshaller marshaller = (CastorMarshaller) context.getBean("castorMarshaller");

        testObjectToXml(marshaller);
//        testXmlToObject(marshaller);
    }

    private static void testObjectToXml(CastorMarshaller marshaller) throws Exception {

        Mobile mobileObject = new Mobile();
        mobileObject.setName("Nokia");
        mobileObject.setModel("N8");
        mobileObject.setPrice(32323D);

        FileOutputStream outputStream = new FileOutputStream(new File("D:/mobile.xml"));
        StreamResult xmlFileResult = new StreamResult(outputStream);

        marshaller.marshal(mobileObject, xmlFileResult);
    }

    private static void testXmlToObject(CastorMarshaller marshaller) throws Exception {


        FileInputStream inputStream = new FileInputStream(new File("mobile.xml"));
        StreamSource xmlFileSource = new StreamSource(inputStream);

        marshaller.setTargetClass(Mobile.class);
        Mobile mobileObject = (Mobile) marshaller.unmarshal(xmlFileSource);

        System.out.println("Name is " + mobileObject.getName());
        System.out.println("Model is " + mobileObject.getModel());
        System.out.println("Price is " + mobileObject.getPrice());
    }
}
