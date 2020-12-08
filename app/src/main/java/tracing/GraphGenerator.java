package tracing;

import java.util.HashMap;
import java.util.Map;

public final class GraphGenerator {
    private GraphGenerator() {
        // utility class
    }

    public static Map<String, Node> generateNodesFrom(final String input) {
        String[] attributes = input.split("Graph: ")[1].split(", ");
        Map<String, Node> nodeIdToNodeMap = new HashMap<>();
        for (String attribute : attributes) {
            Node src = putNodeIfNew(nodeIdToNodeMap, attribute.substring(0, 1));
            Node dest = putNodeIfNew(nodeIdToNodeMap, attribute.substring(1, 2));
            int weight = Integer.parseInt(attribute.substring(2));
            addEdgeToSourceNode(new Edge(src, dest, weight));
        }

        return nodeIdToNodeMap;
    }

    private static Node putNodeIfNew(
            final Map<String, Node> nodeIdToNodeMap, final String id
    ) {
        if (nodeIdToNodeMap.containsKey(id)) {
            return nodeIdToNodeMap.get(id);
        }

        Node node = new Node(id);
        nodeIdToNodeMap.put(id, node);
        return node;
    }

    private static void addEdgeToSourceNode(final Edge edge) {
        edge.src.addEdge(edge);
    }
}
