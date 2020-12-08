package tracing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ShortestTraceCalculator {
    private ShortestTraceCalculator() {
        // utility class
    }

    /**
     * Dijkstra's algorithm https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     * with a slight variation to account for cycles. In this case it is assume that the node distance cannot be 0
     */
    public static int calculateShortestTrace(final String start, final String end, final Map<String, Node> nodeIdToNodeMap) {
        Map<String, Integer> nodeDistances = new HashMap<>();
        for (Node node : nodeIdToNodeMap.values()) {
            nodeDistances.put(node.id, Integer.MAX_VALUE);
        }
        nodeDistances.put(start, 0);

        Set<Node> unvistedNodes = new HashSet<>(nodeIdToNodeMap.values());

        while (!unvistedNodes.isEmpty()) {
            int minDistance = Integer.MAX_VALUE;
            Node currentNode = unvistedNodes.iterator().next();
            for (Node unvistedNode : unvistedNodes) {
                int unvistedNodeDistance = nodeDistances.get(unvistedNode.id);
                if (unvistedNodeDistance < minDistance) {
                    minDistance = unvistedNodeDistance;
                    currentNode = unvistedNode;
                }
            }

            unvistedNodes.remove(currentNode);

            if (end.equals(currentNode.id) && nodeDistances.get(end) != 0) {
                break;
            }

            for (Edge edge : currentNode.getAdjacentEdges()) {
                int currentDistance = nodeDistances.get(currentNode.id);
                if (currentDistance != Integer.MAX_VALUE) {
                    int existingDistanceToDest = nodeDistances.get(edge.dest.id);
                    int distanceToDestFromCurrent = currentDistance + edge.weight;
                    if (distanceToDestFromCurrent < existingDistanceToDest || existingDistanceToDest == 0) {
                        nodeDistances.put(edge.dest.id, distanceToDestFromCurrent);
                    }
                }
            }

        }

        return nodeDistances.get(end);
    }
}
