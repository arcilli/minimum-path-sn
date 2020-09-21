import java.util.List;

public interface AbstractPerson {

    int getId();

    List<Integer> getFriendsId();

    void addFriend(int id);
}
