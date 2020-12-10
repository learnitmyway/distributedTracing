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
     * with a slight variation to account for cycles. In this case it is assumes that the node distance cannot be 0
     */
    public static int calculateShortestTrace(final String start, final String end, final Map<String, Node> nodeIdToNodeMap) {
        Map<String, Integer> nodeDistances = new HashMap<>();
        for (Node node : nodeIdToNodeMap.values()) {
            nodeDistances.put(node.id, Integer.MAX_VALUE);
        }
        nodeDistances.put(start, 0);

        Set<Node> unvisitedNodes = new HashSet<>(nodeIdToNodeMap.values());

        while (!unvisitedNodes.isEmpty()) {
            Node unvisitedNodeWithMinDistance = unvisitedNodes.stream().reduce(unvisitedNodes.iterator().next(), (acc, unvisitedNode) -> {
                int unvisitedNodeDistance = nodeDistances.get(unvisitedNode.id);
                int minDistance = nodeDistances.get(acc.id);
                if (unvisitedNodeDistance < minDistance) {
                    return unvisitedNode;
                }

                return acc;
            });

            unvisitedNodes.remove(unvisitedNodeWithMinDistance);

            // we already have reached our destination
            if (end.equals(unvisitedNodeWithMinDistance.id) && nodeDistances.get(end) != 0) {
                break;
            }

            for (Edge edge : unvisitedNodeWithMinDistance.getAdjacentEdges()) {
                int currentDistance = nodeDistances.get(unvisitedNodeWithMinDistance.id);
                if (currentDistance != Integer.MAX_VALUE) {
                    int existingDistanceToDest = nodeDistances.get(edge.dest.id);
                    int distanceToDestFromCurrent = currentDistance + edge.weight;
                    if (distanceToDestFromCurrent < existingDistanceToDest) {
                        nodeDistances.put(edge.dest.id, distanceToDestFromCurrent);
                    }
                }
            }

            // reset start
            if (nodeDistances.get(start) == 0) {
                nodeDistances.put(start, Integer.MAX_VALUE);
            }
        }

        return nodeDistances.get(end);
    }
}
