import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements AbstractPerson {

    //The ids are incremental, so we got the number of the persons from the network at the end.
    private int id = 0;

    private static int SEQUENCE = 0;
    private final String name;

    // Friends ids.
    // The cost from every 2 friends is 1.
    List<Integer> friendsId;

    public Person(String name) {
        id = SEQUENCE++;
        friendsId = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void addFriend(int id) {
        friendsId.add(id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Integer> getFriendsId() {
        return friendsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(friendsId, person.friendsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, friendsId);
    }

    @Override
    public String toString() {
        return "Person: " + id + "\n" + "Friends: " +
                friendsId + "\n";
    }
}
