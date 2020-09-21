import java.util.List;

public interface AbstractNetwork {
    void addPerson(AbstractPerson abstractPerson);

    List<Integer> findMinPath(AbstractPerson source, AbstractPerson destination);
}
