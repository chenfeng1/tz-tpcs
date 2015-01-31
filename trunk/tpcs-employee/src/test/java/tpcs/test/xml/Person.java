package tpcs.test.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hu Jing Ling on 2015/1/26.
 */
public class Person {

    private String firstname = "";

    private String lastname = "";

    private boolean developer = false;

    private List<Person> friends = new ArrayList<Person>();

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public void setDeveloper(boolean developer) {
        this.developer = developer;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public void addFriend(Person friend) {
        friends.add(friend);
    }

}
