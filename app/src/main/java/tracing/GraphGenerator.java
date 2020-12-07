package tracing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class GraphGenerator {
    private GraphGenerator() {
        // utility class
    }

    public static Set<Node> generateGraphFrom(final String input) {
        String[] attributes = input.split(", ");
        Map<String, Node> nodes = new HashMap<>();
        for (String attribute : attributes) {
            Node src = addNodeIfNew(nodes, attribute.substring(0, 1));
            Node dest = addNodeIfNew(nodes, attribute.substring(1, 2));
            int weight = Integer.parseInt(attribute.substring(2));
            GraphGenerator.addEdgeToSourceNode(new Edge(src, dest, weight));
        }

        return new HashSet<>(nodes.values());
    }

    private static Node addNodeIfNew(
            final Map<String, Node> nodes, final String id
    ) {
        if (nodes.containsKey(id)) {
            return nodes.get(id);
        }

        Node node = new Node(id);
        nodes.put(id, node);
        return node;
    }

    private static void addEdgeToSourceNode(final Edge edge) {
        edge.src.addEdge(edge);
    }
}
