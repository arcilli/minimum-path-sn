import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Network implements AbstractNetwork {

    // Assume there is no negative path cost.
    private final int UNDEFINED = -1;

    private final int MAX_VALUE = 1 << 15;

    // The "friendship" relation is unidirectional (like Twitter following relation).
    List<AbstractPerson> persons;

    public Network() {
        persons = new ArrayList<>();
    }

    @Override
    public void addPerson(AbstractPerson abstractPerson) {
        persons.add(abstractPerson);
    }

    @Override
    public List<Integer> findMinPath(AbstractPerson source, AbstractPerson destination) {
        // Implement Dijkstra.
        Queue<AbstractPerson> verticesToVisit = new LinkedList<>(persons);

        int noVertices = verticesToVisit.size();
        int[] dist = new int[noVertices];
        int[] prev = new int[noVertices];

        for (int i = 0; i < noVertices; ++i) {
            dist[i] = MAX_VALUE;
            prev[i] = UNDEFINED;
        }

        // The distance from source to source is 0.
        dist[source.getId()] = 0;

        while (!verticesToVisit.isEmpty()) {
            // Find the vertex with minimum distance.

            // We can use a minheap instead of a queue in order to optimize the search of the vertex with minimum distance.
            int indexOfVertexWithMinDistance = findMinimumDistanceFromQueue(dist, verticesToVisit);
            AbstractPerson vertexWithMinimumDistance = null;
            for (AbstractPerson abstractPerson : verticesToVisit) {
                if (abstractPerson.getId() == indexOfVertexWithMinDistance) {
                    vertexWithMinimumDistance = abstractPerson;
                    break;
                }
            }
            if (null != vertexWithMinimumDistance) {
                verticesToVisit.remove(vertexWithMinimumDistance);

                if (vertexWithMinimumDistance.equals(destination)) {
                    // Read the path; we just found a solution.
                    return getReversePath(source.getId(), destination.getId(), prev);
                }

                for (int i : vertexWithMinimumDistance.getFriendsId()) {
                    // The cost from every 2 neighbours is 1.
                    int aux = dist[vertexWithMinimumDistance.getId()] + 1;
                    if (aux < dist[i]) {
                        dist[i] = aux;
                        prev[i] = vertexWithMinimumDistance.getId();
                    }
                }
            }
        }
        return null;
    }

    int findMinimumDistanceFromQueue(int[] dist, Queue<AbstractPerson> queue) {
        List<Integer> indexesFromQueue = queue.stream().map(AbstractPerson::getId).collect(Collectors.toList());
        int minIndex = -1;
        int min = MAX_VALUE;

        for (int i = 0; i < dist.length; ++i) {
            if (min > dist[i] && indexesFromQueue.contains(i)) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private List<Integer> getReversePath(int sourceId, int destId, int[] prev) {
        LinkedList<Integer> stack = new LinkedList<>();

        while (prev[destId] != UNDEFINED || destId != sourceId) {
            stack.offerFirst(destId);
            destId = prev[destId];
        }

        stack.offerFirst(sourceId);
        return stack;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Network: ").append("\n");
        for (AbstractPerson person : persons) {
            stringBuilder.append(person).append("\n");
        }
        return stringBuilder.toString();
    }
}
