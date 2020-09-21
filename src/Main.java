public class Main {
    public static void main(String[] args) {
        AbstractNetwork network = new Network();

        AbstractPerson a = new Person("A");
        AbstractPerson b = new Person("B");
        AbstractPerson c = new Person("C");
        AbstractPerson d = new Person("D");
        AbstractPerson e = new Person("E");
        AbstractPerson f = new Person("F");

        a.addFriend(b.getId());
        a.addFriend(d.getId());

        b.addFriend(c.getId());

        c.addFriend(d.getId());

        d.addFriend(e.getId());
        d.addFriend(c.getId());

        network.addPerson(a);
        network.addPerson(b);
        network.addPerson(c);
        network.addPerson(d);
        network.addPerson(e);
        network.addPerson(f);

        System.out.println(network);

        // The case when there is no direct path between 2 vertices.
        System.out.println(network.findMinPath(a, e));
        System.out.println(network.findMinPath(a, c));

        // The case when the node is isolated in the network.
        System.out.println(network.findMinPath(a, f));

        // Direct neighbours
        System.out.println(network.findMinPath(a, b));
        System.out.println(network.findMinPath(d, c));
        System.out.println(network.findMinPath(c, d));

    }
}
