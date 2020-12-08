package tracing;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class AverageLatencyCalculator {
    private AverageLatencyCalculator() {
        // utility class
    }

    public static int calculateLatency(final List<String> path, final Map<String, Node> nodeIdToNodeMap) {
        int averageLatency = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String srcId = path.get(i);
            String destId = path.get(i + 1);
            Node sourceNode = nodeIdToNodeMap.get(srcId);
            Optional<Edge> edgeWithMatchingDest =
                    findEdgeWithMatchingDest(destId, sourceNode);
            if (edgeWithMatchingDest.isPresent()) {
                averageLatency += edgeWithMatchingDest.get().weight;
            } else {
                return -1;
            }
        }
        return averageLatency;
    }

    private static Optional<Edge> findEdgeWithMatchingDest(final String destId, final Node srcNode) {
        return srcNode.getAdjacentEdges()
                .stream()
                .filter(edge -> destId.equals(edge.dest.id))
                .findFirst();
    }
}
