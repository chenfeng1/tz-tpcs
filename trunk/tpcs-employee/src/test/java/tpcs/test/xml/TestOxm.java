package tpcs.test.xml;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/23 19:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:xml/spring-oxm.xml")
@Ignore
public class TestOxm {

    @Resource
    private OxMapper oxMapper;

    @Test
    public void test1() throws IOException {
        Person person = new Person();
        person.setFirstname("Phil");
        person.setLastname("Steffensen");
        person.setDeveloper(true);

        String filename = "d:/person.xml";
        // Serialization
        oxMapper.writeObjectToXml(person, filename);

        // Deserialization
        Person deserializedPerson = (Person) oxMapper.readObjectFromXml(filename);

        System.out.println("Firstname: " + deserializedPerson.getFirstname());
        System.out.println("Lastname : " + deserializedPerson.getLastname());
        System.out.println("Developer: " + deserializedPerson.isDeveloper());
    }

    @Test
    public void test2() throws IOException {
        Person person = new Person();
        person.setFirstname("Phil");
        person.setLastname("Steffensen");
        person.setDeveloper(true);

        Person friend1 = new Person();
        friend1.setFirstname("Christian");
        friend1.setLastname("Harms");
        friend1.setDeveloper(true);

        person.addFriend(friend1);

        Person friend2 = new Person();
        friend2.setFirstname("John");
        friend2.setLastname("Doe");
        friend2.setDeveloper(false);

        person.addFriend(friend2);

        String filename = "d:/person_with_friends.xml";
        oxMapper.writeObjectToXml(person, filename);

        Person deserializedPerson = (Person) oxMapper.readObjectFromXml(filename);

        System.out.println("Firstname: " + deserializedPerson.getFirstname());
        System.out.println("Lastname : " + deserializedPerson.getLastname());
        System.out.println("Developer: " + deserializedPerson.isDeveloper());
        System.out.println("Friends  : " + deserializedPerson.getFriends().size());
    }

}
