package tracing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ShortestTraceCalculator {
    private ShortestTraceCalculator() {
        // utility class
    }

    // Dijkstra's algorithm https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    public static int calculateShortestTrace(final String start, final String end, final Map<String, Node> nodes) {
        Map<String, Integer> nodeDistances = new HashMap<>();
        for (Node node : nodes.values()) {
            nodeDistances.put(node.id, Integer.MAX_VALUE);
        }
        nodeDistances.put(start, 0);

        Set<Node> unvistedNodes = new HashSet<>(nodes.values());

        while (!unvistedNodes.isEmpty()) {
            int minDistance = Integer.MAX_VALUE;
            Node nodeWithMinDistance = unvistedNodes.iterator().next();
            for (Node unvistedNode : unvistedNodes) {
                Integer unvistedNodeDistance = nodeDistances.get(unvistedNode.id);
                if (unvistedNodeDistance < minDistance) {
                    minDistance = unvistedNodeDistance;
                    nodeWithMinDistance = unvistedNode;
                }
            }

            for (Edge edge : nodeWithMinDistance.getAdjacentEdges()) {
                int potentialMinDistance = nodeDistances.get(nodeWithMinDistance.id) + edge.weight;
                if (potentialMinDistance < nodeDistances.get(edge.dest.id)) {
                    nodeDistances.put(edge.dest.id, potentialMinDistance);
                }
            }

            unvistedNodes.remove(nodeWithMinDistance);

            if (end.equals(nodeWithMinDistance.id)) {
                break;
            }
        }

        return nodeDistances.get(end);
    }
}
