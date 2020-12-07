package tracing;

import java.util.ArrayList;
import java.util.List;

public final class Node {
    public final String id;
    private final List<Edge> adjacentEdges;

    public Node(final String id) {
        this.id = id;
        this.adjacentEdges = new ArrayList<>();
    }

    public void addEdge(final Edge edge) {
        this.getAdjacentEdges().add(edge);
    }

    public List<Edge> getAdjacentEdges() {
        return adjacentEdges;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                '}';
    }
}
